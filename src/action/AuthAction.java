package action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import model.AuthBean;

public class AuthAction extends ActionSupport implements SessionAware {
	
	private String name, email, password;
	private Map<String,Object> session;
	
	public String registerAccount()
	{
		if(this.getAuthBean().registerAccount(name,email,password))
		{
			session.put("loggedin", true);
			session.put("email", email);
			session.put("name", name);
			return SUCCESS;
		}
		else
			return INPUT;
			
	}
	
	public String loginAccount()
	{
		return SUCCESS;
	}
	
	private AuthBean getAuthBean() {
		if(!this.session.containsKey("authBean"))
			this.session.put("authBean", new AuthBean());
		return (AuthBean) this.session.get("authBean");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

}
