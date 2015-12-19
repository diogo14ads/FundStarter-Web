package action;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import model.Project;
import model.RMIBean;
import model.Reward;
import websockets.WebSocketClientEndPoint;
import model.ChatMessage;
import model.Level;

public class ProjectPageAction extends ActionSupport implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7700231974219443500L;
	private int projectId;
	private Project project;
	private ArrayList<Reward> activeRewards;
	private Map<String,Object> session;
	private ArrayList<ChatMessage> messages;
	
	private String sendMessage;
	
	
	
	private HashMap<Level, ArrayList<Reward>> extraLevels;

	public String execute()
	{
		activeRewards = this.getRMIBean().getActiveRewards(projectId);
		project = this.getRMIBean().getProject(projectId);
		extraLevels = this.getRMIBean().getExtraLevels(projectId);
		messages = this.getRMIBean().getChatMessages(projectId, (String) this.session.get("email"));
		return INPUT;
	}
	
	public String projectDetails()
	{
		activeRewards = this.getRMIBean().getActiveRewards(projectId);
		project = this.getRMIBean().getProject(projectId);
		extraLevels = this.getRMIBean().getExtraLevels(projectId);
		return INPUT;
	}
	
	
	public String sendMessage()
	{
		if(this.getRMIBean().sendMessagetoProject((String) this.session.get("email"), projectId, sendMessage))
		{

			try {
				final WebSocketClientEndPoint clientEndPoint = new WebSocketClientEndPoint(new URI("ws://localhost:8080/FundStarter-Web/websockets"));

				clientEndPoint.sendMessage("notify "+projectId+" You Have a new Message from user "+((String) this.session.get("email"))+".");
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return SUCCESS;
		}
		else
			return ERROR;
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

	public HashMap<Level, ArrayList<Reward>> getExtraLevels() {
		return extraLevels;
	}

	public void setExtraLevels(HashMap<Level, ArrayList<Reward>> extraLevels) {
		this.extraLevels = extraLevels;
	}

	public ArrayList<ChatMessage> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<ChatMessage> messages) {
		this.messages = messages;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}


	
	
}
