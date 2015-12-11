package action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import model.RMIBean;
import model.User;

public class AuthAction extends ActionSupport implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7477463623013154947L;

	private User user;

	private Map<String,Object> session;
	
	public String registerAccount()
	{
		System.out.println(user.toString());
		if(this.getRMIBean().registerAccount(user.getName(),user.getEmail(),user.getPassword()))
		{
			session.put("loggedin", true);
			session.put("email", user.getEmail());
			session.put("name", user.getName());
			return SUCCESS;
		}
		else
			return INPUT;
	}
	
	public String login()
	{
		if(this.getRMIBean().verifyLogin(user.getEmail(),user.getPassword())){
			session.put("loggedin", true);
			session.put("email", user.getEmail());
			session.put("name", user.getName());
			return SUCCESS;
		}
		else
			return INPUT;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

}
