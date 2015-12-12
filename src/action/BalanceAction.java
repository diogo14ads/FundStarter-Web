package action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import model.RMIBean;

public class BalanceAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = -3724990176677863785L;
	private Map<String,Object> session;
	private int balance;
	
	public String execute()
	{
		this.balance = this.getRMIBean().checkBalance((String) session.get("email"));
		
		return SUCCESS;
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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
