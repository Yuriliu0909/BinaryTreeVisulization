package NameBT;

import javafx.scene.control.Label;

public class Person extends Label{
	private String firstname;
	private String surname;
	private Person before;
	private Person after;
	
	public Person(String firstname, String surname,Person before, Person after) {
		this.firstname = firstname;
		this.surname = surname;
		this.before = before;
		this.after = after;

	}
	
	public Person(String firstname,String surname) {
		this.firstname = firstname;
		this.surname = surname;
	}
	

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Person getBefore() {
		return before;
	}

	public void setBefore(Person before) {
		this.before = before;
	}

	public Person getAfter() {
		return after;
	}

	public void setAfter(Person after) {
		this.after = after;
	}

	public String toString() {
		String txt = String.valueOf(firstname.charAt(0))+String.valueOf(surname.charAt(0));
		return txt;
	}
}
