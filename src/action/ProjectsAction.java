package action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import model.Project;
import model.RMIBean;

public class ProjectsAction extends ActionSupport implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -790583386762368260L;
	private ArrayList<Project> current;
	private ArrayList<Project> past;
	private Map<String,Object> session;
	private Project newProject;
	
	public String execute()
	{
		this.current = this.getRMIBean().getCurrentProjects();
		this.past = this.getRMIBean().getPastProjects();
		
		return SUCCESS;
	}
	
	public String createProject()
	{
		boolean success;
		success = this.getRMIBean().createNewProject(newProject,(String) this.session.get("email"));
		if(success)
			return SUCCESS;
		else
			return INPUT;
	}

	private RMIBean getRMIBean() {
		if(!this.session.containsKey("rmiBean"))
			this.session.put("rmiBean", new RMIBean());
		return (RMIBean) this.session.get("rmiBean");
	}

	public ArrayList<Project> getCurrent() {
		return current;
	}

	public void setCurrent(ArrayList<Project> current) {
		this.current = current;
	}

	public ArrayList<Project> getPast() {
		return past;
	}

	public void setPast(ArrayList<Project> past) {
		this.past = past;
	}
	
	public Project getNewProject() {
		return newProject;
	}

	public void setNewProject(Project newProject) {
		this.newProject = newProject;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

}
