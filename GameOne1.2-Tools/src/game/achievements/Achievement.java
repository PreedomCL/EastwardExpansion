package game.achievements;

public abstract class Achievement {
	
	private String name, goal;
	private boolean complete = false, messageShown = false;
	public Achievement(String name, String goal) {
		
	}
	
	public abstract void tick();
	
	
	//G&S
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public boolean isMessageShown() {
		return messageShown;
	}

	public void setMessageShown(boolean messageShown) {
		this.messageShown = messageShown;
	}
	
	
}
