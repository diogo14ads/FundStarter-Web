package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Properties;

import common.DatabaseRow;
import common.RMIInterface;

public class RMIBean {


	static int RMIRegistry;
    static String rmiLookup;
	RMIInterface server;
	static Properties prop;
	
	public RMIBean() {
		super();
		
		readProperties();
		RMIRegistry = Integer.parseInt(prop.getProperty("RmiRegistry"));
        rmiLookup = prop.getProperty("RmiLookup");
		try {
			
			server = (RMIInterface) LocateRegistry.getRegistry(RMIRegistry).lookup(rmiLookup);
			System.out.println("Connected to RMI.");
			
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void readProperties() {
		
		prop = new Properties();

		InputStream input = null;
		try {
			//TODO nao estava a encontrar o ficheiro, pus o caminho todo
			input = new FileInputStream("/home/diogo/DEI1516/SD/FundStarter-Web/config.properties");
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<Project> getCurrentProjects()
	{
		try {
			return toProjectArraylist(server.currentProjectsList());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Project> getPastProjects() {
		try {
			return toProjectArraylist(server.pastProjectsList());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Project> getMyProjects(String email)
	{
		try {
			return toProjectNameIdArraylist(this.server.myProjectsList(email));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	//podia criar esta função no servidor, mas não quero mexer no código do projecto anterior
	public Project getProject(int projectId)
	{
		ArrayList<Project> list = getCurrentProjects();
		
		for(Project curr : list)
		{
			if(curr.getProjectId() == projectId)
				return curr;
		}
		
		return null;
	}
	public ArrayList<Project> toProjectNameIdArraylist(ArrayList<DatabaseRow> list)
	{
		ArrayList<Project> newList = new ArrayList<Project>();
		TypeConverter aux;
		
		for(DatabaseRow row : list)
		{
			aux = new TypeConverter(row.getColumns());
			newList.add(aux.toProjectNameId());
		}
		
		return newList;
	}
	
	public ArrayList<Project> toProjectArraylist(ArrayList<DatabaseRow> list)
	{
		ArrayList<Project> newList = new ArrayList<Project>();
		TypeConverter aux;
		
		for(DatabaseRow row : list)
		{
			aux = new TypeConverter(row.getColumns());
			newList.add(aux.toProject());
		}
		
		return newList;
	}

	public boolean registerAccount(String name, String email, String password) {
		try {
			return server.register(name, email, password);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


	public boolean verifyLogin(String email, String password) {
		try {
			return server.login(email, password);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public int checkBalance(String email)
	{
		try {
			return server.checkBalance(email);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public ArrayList<MyReward> getMyRewards(String email)
	{
		try {
			return toMyRewaradArraylist(server.getMyRewards(email));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Reward> getActiveRewards(int projectId)
	{
		try {
			return toRewaradArraylist(server.activeRewardsList(projectId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Level> getProjectLevels(int projectId) {
		try {
			return toLevelArraylist(this.server.projectLevelsList(projectId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	private ArrayList<Level> toLevelArraylist(ArrayList<DatabaseRow> list) {
		ArrayList<Level> newList = new ArrayList<Level>();
		TypeConverter aux;
		
		for(DatabaseRow row : list)
		{
			aux = new TypeConverter(row.getColumns());
			newList.add(aux.toLevel());
		}
		
		return newList;
	}


	public ArrayList<MyReward> toMyRewaradArraylist(ArrayList<DatabaseRow> list)
	{
		ArrayList<MyReward> newList = new ArrayList<MyReward>();
		TypeConverter aux;
		
		for(DatabaseRow row : list)
		{
			aux = new TypeConverter(row.getColumns());
			newList.add(aux.toMyReward());
		}
		
		return newList;
	}
	
	public ArrayList<Reward> toRewaradArraylist(ArrayList<DatabaseRow> list)
	{
		ArrayList<Reward> newList = new ArrayList<Reward>();
		TypeConverter aux;
		
		for(DatabaseRow row : list)
		{
			aux = new TypeConverter(row.getColumns());
			newList.add(aux.toReward());
		}
		
		return newList;
	}


	public boolean createNewProject(Project newProject, String creatorEmail) {
		
		boolean success;
		//TODO criar validate para verificar os dados
		try {
			success = this.server.createProject(newProject.getProjectName(), newProject.getProjectDescription(), newProject.getDateEnd()
					, Integer.toString(newProject.getObjective()), creatorEmail);
			return success;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean pledge(int rewardId, String email)
	{
		try {
			return this.server.buyReward(rewardId, email);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


	public boolean cancelProject(int projectId) {
		try {
			return this.server.cancelProject(projectId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
}
