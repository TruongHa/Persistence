package org.persistence.model;

public class AbstractEntity {

	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[id=" + id + "]";
	}
	
	

}
