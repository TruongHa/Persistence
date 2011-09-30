package org.persistence;

import org.persistence.context.PersistenceContext;
import org.persistence.model.Person;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		PersistenceContext.init();
		Person person = new Person();
		person.setName("bndc");
		Person person2 = new Person();
		person2.setName("th");
		PersistenceContext.merge(person);
		PersistenceContext.merge(person2);
		PersistenceContext.remove(person2);
		Person fp = PersistenceContext.get(2, Person.class);
		System.out.println(fp);
		PersistenceContext.close();
	}
}
