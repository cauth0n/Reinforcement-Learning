package Racetrack;

import java.util.ArrayList;

public abstract class Learner {
	protected MDP mdp;
	protected double error;
	protected int movementReward = -1;
	protected ArrayList<Q> qValues;
	protected double transitionProb;
	
	public Learner(MDP mdp, double error) {
		this.mdp = mdp;
		this.error = error;
		this.transitionProb = mdp.getTransitionProb();
	}
	
	public double reward(State state){
		double value = -1;
		
		return value;
	}

}
