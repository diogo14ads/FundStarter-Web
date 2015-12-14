package model;

public class Reward {

	private int rewardId;
	private int value;
	private String description;
	public Reward() {
		super();
	}
	public Reward(int id, int value, String description) {
		super();
		this.rewardId = id;
		this.value = value;
		this.description = description;
	}
	public int getRewardId() {
		return rewardId;
	}
	public void setRewardId(int id) {
		this.rewardId = id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
