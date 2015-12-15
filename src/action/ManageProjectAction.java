package action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import model.Level;
import model.Project;
import model.RMIBean;

public class ManageProjectAction extends ActionSupport implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8402811576563469463L;
	private Map<String,Object> session;
	private ArrayList<Project> myProjects;
	
	private int projectId;
	private ArrayList<Level> projectLevels;
	
	private int objective;
	
	private int levelId;
	
	private String newAdmin;
	
	public String myProjects()
	{
		myProjects = this.getRMIBean().getMyProjects((String) this.session.get("email"));
		return SUCCESS;
	}
	
	public String manage()
	{
		this.projectLevels = this.getRMIBean().getProjectLevels(projectId);
		
		
		return SUCCESS;
	}
	
	public String cancelProject()
	{
		this.getRMIBean().cancelProject(projectId);
		return SUCCESS;
	}
	
	public String addRewardLevel()
	{
		this.getRMIBean().addRewardLevel(projectId,objective);
		return SUCCESS;
	}
	
	public String removeRewardLevel()
	{
		//TODO adicionar validate, não pode ser eliminado reward com levelId=0
		this.getRMIBean().removeRewardLevel(levelId);
		return SUCCESS;
	}
	
	public String addAdministrator()
	{
		this.getRMIBean().addAdministrator(projectId,newAdmin);
		return SUCCESS;
	}

	/*-----------------------------------SETTERS and GETTERS-----------------------------------------------------------*/
	
	private RMIBean getRMIBean() {
		if(!this.session.containsKey("rmiBean"))
			this.session.put("rmiBean", new RMIBean());
		return (RMIBean) this.session.get("rmiBean");
	}
	

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public ArrayList<Project> getMyProjects() {
		return myProjects;
	}

	public void setMyProjects(ArrayList<Project> myProjects) {
		this.myProjects = myProjects;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public ArrayList<Level> getProjectLevels() {
		return projectLevels;
	}

	public void setProjectLevels(ArrayList<Level> projectLevels) {
		this.projectLevels = projectLevels;
	}

	public int getObjective() {
		return objective;
	}

	public void setObjective(int objective) {
		this.objective = objective;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public String getNewAdmin() {
		return newAdmin;
	}

	public void setNewAdmin(String newAdmin) {
		this.newAdmin = newAdmin;
	}
}
