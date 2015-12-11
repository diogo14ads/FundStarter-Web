package model;

public class AuthBean {
	
	private RMIBean rmi;

	public AuthBean() {
		super();
		this.rmi = new RMIBean();
	}
/*
	public boolean registerAccount(String name, String email, String password) {
		return rmi.registerAccount(name,email,password);
	}

	public RMIBean getRmi() {
		return rmi;
	}

	public void setRmi(RMIBean rmi) {
		this.rmi = rmi;
	}
	*/
}
