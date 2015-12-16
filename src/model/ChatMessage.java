package model;

public class ChatMessage {
	
	private int messageId;
	private int idProject;
	private String email;
	private String messageText;
	private String messageDate;
	public ChatMessage() {
		super();
	}
	public ChatMessage(int messageId, int idProject, String email, String messageText, String messageDate) {
		super();
		this.messageId = messageId;
		this.idProject = idProject;
		this.email = email;
		this.messageText = messageText;
		this.messageDate = messageDate;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getIdProject() {
		return idProject;
	}
	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public String getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	@Override
	public String toString() {
		return "ChatMessage [messageId=" + messageId + ", idProject=" + idProject + ", email=" + email
				+ ", messageText=" + messageText + ", messageDate=" + messageDate + "]";
	}

}
