package org.persistence.context;

import org.persistence.model.AbstractEntity;

public class ProxyModel {

	public static final Integer NEW = 0;
	public static final Integer UPDATED = 1;
	public static final Integer DELETE = 2;

	private AbstractEntity object;
	private Integer status;
	
	public ProxyModel(AbstractEntity model){
		this.object = model;
	}

	public AbstractEntity getObject() {
		return object;
	}

	public void setObject(AbstractEntity object) {
		this.object = object;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return object.getClass().getSimpleName() + "[id=" + object.getId() + "]";
	}
	
	

}
