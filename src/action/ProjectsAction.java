package action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import common.DatabaseRow;
import model.ProjectsBean;

public class ProjectsAction extends ActionSupport implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -790583386762368260L;
	private ArrayList<DatabaseRow> current;
	private ArrayList<DatabaseRow> past;
	private Map<String,Object> session;
	
	public String execute()
	{
		System.out.println("coise");
		this.current = this.getProjectsBean().getCurrentProjects();
		this.past = this.getProjectsBean().getPastProjects();
		return SUCCESS;
	}

	private ProjectsBean getProjectsBean() {
		if(!this.session.containsKey("projectsBean"))
			this.setProjectsBean(new ProjectsBean());
		return (ProjectsBean) this.session.get("projectsBean");
	}

	private void setProjectsBean(ProjectsBean projectsBean) {
		this.session.put("projectsBean", projectsBean);
	}
	
	public ArrayList<DatabaseRow> getCurrent() {
		return current;
	}

	public void setCurrent(ArrayList<DatabaseRow> current) {
		this.current = current;
	}

	public ArrayList<DatabaseRow> getPast() {
		return past;
	}

	public void setPast(ArrayList<DatabaseRow> past) {
		this.past = past;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

}
