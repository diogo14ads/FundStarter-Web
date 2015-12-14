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

}
