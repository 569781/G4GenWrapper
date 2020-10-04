package com.gestion400.g4genwrapper.acciones;

import java.util.*;

import javax.ejb.*;
import javax.inject.*;
import javax.validation.*;
import javax.validation.metadata.*;

import org.apache.commons.logging.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.tab.*;
import org.openxava.util.*;
import org.openxava.validators.ValidationException;

import com.gestion400.wizard.*;


public class ManyToManyAddElementsToCollectionAction extends SaveElementInCollectionAction
implements INavigationAction {

	private static Log log = LogFactory.getLog(AddElementsToCollectionAction.class);
		
	@Inject private Tab tab;
	
	private int added;
	
	private int failed;
	
	private int row = -1;
	
	@Inject private String currentCollectionLabel;
	
	public void execute() throws Exception {
		
		saveIfNotExists(getCollectionElementView().getParent());
		
		if (row >= 0) {
			
			validateMaximum(1); 
			
			associateEntityInRow(row);
			
		} else {
		
			Map [] selectedOnes = getTab().getSelectedKeys();
			
			validateMaximum(selectedOnes.length); 
	
			if (selectedOnes != null && selectedOnes.length > 0) {						
				for (int i = 0; i < selectedOnes.length; i++) {
					associateKey(selectedOnes[i]);
				}
			}		
		}
		
		addMessage("elements_added_to_collection", new Integer(added), currentCollectionLabel);		
		
		if (failed > 0) addError("elements_not_added_to_collection", new Integer(failed), currentCollectionLabel);
		
		getView().setKeyEditable(false); // To mark as saved
		getTab().deselectAll();
		resetDescriptionsCache();
		closeDialog(); 
	}
	
	private void associateEntityInRow(int row) throws FinderException, Exception {
		
		Map key = (Map) getTab().getTableModel().getObjectAt(row);
		
		associateKey(key);
	}
	
	private void associateKey(Map key){
		
		try {	
			
			Class<?> primaryClass = getView().getMetaModel().getPOJOClass();
			
			Object primary = getReference(primaryClass,
					getPreviousView().getValueString(getPreviousView().getSearchKeyName()));
			
			Class<?> secundaryClass = getCollectionElementView().getMetaModel().getPOJOClass();
			
			Object secundary = getReference(secundaryClass,(String)key.get(getView().getSearchKeyName()));
			
			ManyToManyRelationship anotations = primaryClass.getDeclaredField(getCollectionElementView().getMemberName()).getAnnotation(ManyToManyRelationship.class);
			
			Class<? extends IRelationship> clase = anotations.value();
			
			IRelationship relathionship = clase.newInstance();
			
			relathionship.setPrimary(primary);
			
			relathionship.setSecundary(secundary);
			
			if(relathionship.find() == null) {
				
				XPersistence.getManager().persist(relathionship);
				
				added++;
			}
			
		} catch(Exception ex) {
			
			addValidationMessage(ex); 
			
			failed++;
			
			log.error(XavaResources.getString("add_collection_element_error", 
				getCollectionElementView().getModelName(), 
				getCollectionElementView().getParent().getModelName()),ex);			
		}
	}
	
	private <T> T getReference(Class<T> _class,String id) {
		return XPersistence.getManager().find(_class,id);
	}
	
	public void addValidationMessage(Exception ex) { 
		if (ex instanceof ValidationException) {		
			addErrors(((ValidationException)ex).getErrors());
		}  
		else if(ex instanceof ConstraintViolationException){
			addConstraintViolationErrors((ConstraintViolationException) ex);					
		} 
		else if (ex instanceof javax.validation.ValidationException) {
			addError(ex.getMessage());
		}
	}
	
	private void addConstraintViolationErrors(ConstraintViolationException ex) {
		
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		
		for (ConstraintViolation<?> violation : violations) {
			
			String message = violation.getMessage();
			
			if (message.startsWith("{") && message.endsWith("}")) {			
				message = message.substring(1, message.length() - 1); 							
			}
			
			ConstraintDescriptor<?> descriptor = violation.getConstraintDescriptor(); 
			java.lang.annotation.Annotation annotation = descriptor.getAnnotation();
			
			if(annotation instanceof javax.validation.constraints.AssertTrue) {							
				Object bean = violation.getRootBean();				
				addError(message, bean);					
			}
		}
	}
	
	@Override
	public String[] getNextControllers() {		
		return added > 0? PREVIOUS_CONTROLLERS : SAME_CONTROLLERS;
	}

	@Override
	public String getCustomView() {		
		return added > 0 ? PREVIOUS_VIEW : SAME_VIEW; 
	}
	
	public Tab getTab() {
		return tab;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setTab(Tab web) {
		tab = web;
	}
}
