package action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import model.Project;
import model.RMIBean;
import model.Reward;
import model.MyReward;

public class ProjectPageAction extends ActionSupport implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7700231974219443500L;
	private int projectId;
	private Project project;
	private ArrayList<Reward> activeRewards;
	private Map<String,Object> session;

	public String execute()
	{
		activeRewards = this.getRMIBean().getActiveRewards(projectId);
		project = this.getRMIBean().getProject(projectId);
		return INPUT;
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

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public ArrayList<Reward> getActiveRewards() {
		return activeRewards;
	}

	public void setActiveRewards(ArrayList<Reward> activeRewards) {
		this.activeRewards = activeRewards;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	
	
}
