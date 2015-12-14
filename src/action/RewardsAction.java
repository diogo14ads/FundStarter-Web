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
	
	public String execute()
	{
		myRewards = this.getRMIBean().getMyRewards((String) session.get("email"));
		System.out.println(myRewards.get(0));
		return SUCCESS;
	}
	
	public String makePledge()
	{
		if(this.getRMIBean().pledge(rewardId, (String) this.session.get("email")))
			return SUCCESS;
		else
			return ERROR;
	}
	
	
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
	

}
