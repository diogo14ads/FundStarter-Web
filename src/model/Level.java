package model;

public class Level {
	
	private int levelId;
	private int objective;
	public Level() {
		super();
	}
	public Level(int levelId, int objective) {
		super();
		this.levelId = levelId;
		this.objective = objective;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public int getObjective() {
		return objective;
	}
	public void setObjective(int objective) {
		this.objective = objective;
	}
	
	
	
}
