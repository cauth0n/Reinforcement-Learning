package Racetrack;

public abstract class Learner {
	protected MDP mdp;
	protected int error;
	protected int movementReward = -1;
	
	public Learner(MDP mdp, int error) {
		this.mdp = mdp;
		this.error = error;
	}

}
