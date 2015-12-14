package model;

public class MyReward {
	
	private String description;
	private String projectName;
	private int value;
	private boolean isProjectActive;
	private int pledgeId;
	private boolean isSuccessfull;
	
	public MyReward() {
		super();
	}
	
	public MyReward(String description, String name, int value, boolean isProjectActive, int pledgeId,
			boolean isSuccessfull) {
		super();
		this.description = description;
		this.projectName = name;
		this.value = value;
		this.isProjectActive = isProjectActive;
		this.pledgeId = pledgeId;
		this.isSuccessfull = isSuccessfull;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String name) {
		this.projectName = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isProjectActive() {
		return isProjectActive;
	}

	public void setProjectActive(boolean isProjectActive) {
		this.isProjectActive = isProjectActive;
	}

	public int getPledgeId() {
		return pledgeId;
	}

	public void setPledgeId(int pledgeId) {
		this.pledgeId = pledgeId;
	}

	public boolean isSuccessfull() {
		return isSuccessfull;
	}

	public void setSuccessfull(boolean isSuccessfull) {
		this.isSuccessfull = isSuccessfull;
	}

	@Override
	public String toString() {
		return "Reward [description=" + description + ", name=" + projectName + ", value=" + value + ", isProjectActive="
				+ isProjectActive + ", pledgeId=" + pledgeId + ", isSuccessfull=" + isSuccessfull + "]";
	}
	
	
}
