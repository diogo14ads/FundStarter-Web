package model;

import java.util.ArrayList;

import common.DatabaseRow;
import common.Project;

public class TypeConverter extends DatabaseRow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3689498297582147291L;

	public TypeConverter(ArrayList<String> columns) {
		super(columns);
		// TODO Auto-generated constructor stub
	}
	
	public Project toProject()
	{
		Project proj = new Project();
		proj.setProjectId(Integer.parseInt(super.getColumns().get(0)));
		proj.setDateEnd(super.getColumns().get(1));
		proj.setProjectName(super.getColumns().get(2));
		proj.setMoneyRaised(Integer.parseInt(super.getColumns().get(3)));
		proj.setObjective(Integer.parseInt(super.getColumns().get(4)));
		proj.setProjectDescription(super.getColumns().get(5));
		proj.setPercentageComplete(Math.round(((float)proj.getMoneyRaised()/proj.getObjective())*100));
		return proj;

	}

}
