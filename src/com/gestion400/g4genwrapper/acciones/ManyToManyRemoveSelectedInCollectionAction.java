package com.gestion400.g4genwrapper.acciones;

import org.openxava.actions.*;
import org.openxava.jpa.*;

import com.gestion400.wizard.*;

public class ManyToManyRemoveSelectedInCollectionAction extends RemoveSelectedInCollectionAction {
	
	@Override
	public void execute() throws Exception {

		Class<?> primaryClass = getView().getMetaModel().getPOJOClass();
		
		Object primary = getReference(primaryClass,
				getView().getValueString(getView().getSearchKeyName()));
		
		ManyToManyRelationship anotations = primaryClass.getDeclaredField(
				getCollectionElementView().getMemberName()).getAnnotation(ManyToManyRelationship.class);
		
		Class<? extends IRelationship> clase = anotations.value();
		
		IRelationship relathionship;
		
		for(Object secundary : getSelectedObjects()) {
			
			relathionship = clase.newInstance();
			
			relathionship.setPrimary(primary);
			
			relathionship.setSecundary(secundary);
			
			relathionship = relathionship.find();
			
			XPersistence.getManager().remove(relathionship);
		}
		
		commit();				
		addMessage();
		getView().recalculateProperties();
		getCollectionElementView().collectionDeselectAll();
	}
	
	private <T> T getReference(Class<T> _class,String id) {
		return XPersistence.getManager().find(_class,id);
	}
}
