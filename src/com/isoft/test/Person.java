package com.isoft.test;

import java.util.HashSet;
import java.util.Set;

public class Person {
	private String personID;
	private String personName;
	private String personDesc;
	private Set projectSet = new HashSet();
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonDesc() {
		return personDesc;
	}
	public void setPersonDesc(String personDesc) {
		this.personDesc = personDesc;
	}
	public Set getProjectSet() {
		return projectSet;
	}
	public void setProjectSet(Set projectSet) {
		this.projectSet = projectSet;
	}

	
}
