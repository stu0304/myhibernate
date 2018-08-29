package com.isoft.test;

import java.util.HashSet;
import java.util.Set;

public class Project {
	private String projectId;
	private String projectName;
	private String projectDesc;
	private Set personSet = new HashSet();
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	public Set getPersonSet() {
		return personSet;
	}
	public void setPersonSet(Set personSet) {
		this.personSet = personSet;
	}
}
