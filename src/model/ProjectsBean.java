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
		ArrayList<DatabaseRow> current = rmi.getCurrentProjects();
		int raised, objective, percentage;
		
		
		//Calcula a percentagem obtida, para fazer a barra de progresso no jsp
		for(int i=0;i<current.size();i++)
		{
			raised = Integer.parseInt(current.get(i).getColumns().get(3));
			objective = Integer.parseInt(current.get(i).getColumns().get(4));
			percentage = Math.round(((float)raised/objective)*100);
			System.out.println(percentage);
			current.get(i).getColumns().add(Integer.toString(percentage));
		}
		return current;
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
