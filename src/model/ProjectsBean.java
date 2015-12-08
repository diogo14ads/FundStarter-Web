package model;

import java.util.ArrayList;

import common.DatabaseRow;

public class ProjectsBean{
	
	private RMIBean rmi;

	public ProjectsBean() {
		super();
		
		this.rmi = new RMIBean();
		
	}

	public ArrayList<DatabaseRow> getCurrentProjects() {
		return rmi.getCurrentProjects();
	}

	public RMIBean getRmi() {
		return rmi;
	}

	public void setRmi(RMIBean rmi) {
		this.rmi = rmi;
	}

	public ArrayList<DatabaseRow> getPastProjects() {
		return rmi.getPastProjects();
	}

}
