package com.gestion400.g4genwrapper.acciones;

import org.openxava.actions.*;

import com.gestion400.wizard.*;

public class ManyToManyGoAddElementsToCollectionAction extends GoAddElementsToCollectionAction {

	public void execute() throws Exception {
	
		super.execute();
		
		Class<?> primaryClass = getView().getMetaModel().getPOJOClass();
		
		ManyToManyRelationship annotation = primaryClass.getDeclaredField(getCollectionElementView().getMemberName()).getAnnotation(ManyToManyRelationship.class);
		
		getTab().setBaseCondition(annotation.exclusionCondition().replace("?", "'" + getView().getValueString(getView().getSearchKeyName()) + "'"));
	}
}
