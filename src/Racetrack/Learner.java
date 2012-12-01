package Racetrack;

import java.util.ArrayList;

public abstract class Learner {
	protected MDP mdp;
	protected Race_Track raceTrack;
	protected double error;
	protected int movementReward = -1;
	protected ArrayList<Q> qValues;
	protected double transitionProb;
	protected Printer p;

	public Learner(MDP mdp, double error, Race_Track raceTrack) {
		this.mdp = mdp;
		this.error = error;
		this.transitionProb = mdp.getTransitionProb();
		this.raceTrack = raceTrack;
		p = new Printer();
	}

	public double reward(State state) {
		double value = -1;
		if (raceTrack.getTile(state.getPosition().getX(), state.getPosition().getY()) == 'F'){
			value = 1;
		}
		
		return value;
	}

}
