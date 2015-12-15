package action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import model.RMIBean;
import model.MyReward;

public class RewardsAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3724990176677863785L;
	private Map<String,Object> session;
	private ArrayList<MyReward> myRewards;
	
	private int rewardId;
	
	private int pledgeId;
	private String recepientEmail;
	
	private int projectId;
	private int levelId;
	private String description;
	private int value;
	
	public String execute()
	{
		myRewards = this.getRMIBean().getMyRewards((String) session.get("email"));
		return SUCCESS;
	}
	
	public String makePledge()
	{
		if(this.getRMIBean().pledge(rewardId, (String) this.session.get("email")))
			return SUCCESS;
		else
			return ERROR;
	}
	
	public String giveawayReward()
	{
		System.out.println(pledgeId);
		System.out.println(recepientEmail);
		this.getRMIBean().giveawayReward(pledgeId,recepientEmail);
		return SUCCESS;
	}
	
	public String removeReward()
	{
		System.out.println(this.getRMIBean().removeReward(rewardId));
		return SUCCESS;
	}

	public String addReward()
	{
		this.getRMIBean().addReward(projectId, levelId, description, value);
		return SUCCESS;
	}
	
	/*--------------------------------GETTERS AND SETTERS---------------------------------------------------*/
	
	private RMIBean getRMIBean() {
		if(!this.session.containsKey("rmiBean"))
			this.session.put("rmiBean", new RMIBean());
		return (RMIBean) this.session.get("rmiBean");
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public ArrayList<MyReward> getMyRewards() {
		return myRewards;
	}

	public void setMyRewards(ArrayList<MyReward> myRewards) {
		this.myRewards = myRewards;
	}

	public int getRewardId() {
		return rewardId;
	}

	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}

	public int getPledgeId() {
		return pledgeId;
	}

	public void setPledgeId(int pledgeId) {
		this.pledgeId = pledgeId;
	}

	public String getRecepientEmail() {
		return recepientEmail;
	}

	public void setRecepientEmail(String recepientEmail) {
		this.recepientEmail = recepientEmail;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	

}
