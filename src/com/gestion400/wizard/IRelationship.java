package com.gestion400.wizard;

public interface IRelationship {
	
	public void setPrimary(Object primary);
	
	public void setSecundary(Object secundary);
	
	public IRelationship find();
}
