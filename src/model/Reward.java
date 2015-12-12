package model;

public class Reward {
	
	private String description;
	private String name;
	private int value;
	private boolean isProjectActive;
	private int pledgeId;
	private boolean isSuccessfull;
	
	public Reward() {
		super();
	}
	
	public Reward(String description, String name, int value, boolean isProjectActive, int pledgeId,
			boolean isSuccessfull) {
		super();
		this.description = description;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Reward [description=" + description + ", name=" + name + ", value=" + value + ", isProjectActive="
				+ isProjectActive + ", pledgeId=" + pledgeId + ", isSuccessfull=" + isSuccessfull + "]";
	}
	
	
}
