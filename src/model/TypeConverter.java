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
	
	//Só tem o nome e o ID do projecto
	public Project toProjectNameId()
	{
		Project proj = new Project();
		proj.setProjectId(Integer.parseInt(super.getColumns().get(0)));
		proj.setProjectName(super.getColumns().get(1));
		
		return proj;
	}
	
	//contem informaçao sobre o pledge, e do seu estado
	public MyReward toMyReward()
	{
		MyReward reward = new MyReward();
		reward.setDescription(super.getColumns().get(0));
		reward.setProjectName(super.getColumns().get(1));
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

	//contem so informaçao sobre a reward
	public Reward toReward()
	{
		Reward reward = new Reward();
		reward.setRewardId(Integer.parseInt(super.getColumns().get(0)));
		reward.setDescription(super.getColumns().get(1));
		reward.setValue(Integer.parseInt(this.getColumns().get(2)));
		
		return reward;
		
	}
	
	public Level toLevel()
	{
		Level level = new Level();
		level.setLevelId(Integer.parseInt(super.getColumns().get(0)));
		level.setObjective(Integer.parseInt(super.getColumns().get(1)));
		
		return level;
		
	}
	
	public ChatMessage toChatMessage()
	{
		ChatMessage msg = new ChatMessage();
		msg.setMessageText(super.getColumns().get(0));
		msg.setMessageDate(super.getColumns().get(1));
		msg.setMessageId(Integer.parseInt(super.getColumns().get(2)));
		msg.setEmail(super.getColumns().get(3));
		msg.setIdProject(Integer.parseInt(super.getColumns().get(4)));
		
		return msg;
	}
	
	//só para debug, para ver o que está em cada um dos elementos do arraylist
	public void printColumns()
	{
		for(int i = 0 ; i<this.getColumns().size() ; i++)
		{
			System.out.println(i+" : "+this.getColumns().get(i));
		}
	}

}
