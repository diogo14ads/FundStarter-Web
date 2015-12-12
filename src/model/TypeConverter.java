package model;

import java.util.ArrayList;
import common.DatabaseRow;

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
	
	public Reward toReward()
	{
		printColumns(this);
		Reward reward = new Reward();
		reward.setDescription(super.getColumns().get(0));
		reward.setName(super.getColumns().get(1));
		reward.setValue(Integer.parseInt(this.getColumns().get(2)));
		
		if(super.getColumns().get(3).equals("t"))
			reward.setProjectActive(true);
		else
			reward.setProjectActive(false);
		
		reward.setPledgeId(Integer.parseInt(this.getColumns().get(4)));
		

		if(super.getColumns().get(5).equals("t"))
			reward.setSuccessfull(true);
		else
			reward.setSuccessfull(false);
		
		return reward;
	}
	
	public void printColumns(DatabaseRow row)
	{
		for(int i = 0 ; i<row.getColumns().size() ; i++)
		{
			System.out.println(i+" : "+row.getColumns().get(i));
		}
	}

}
