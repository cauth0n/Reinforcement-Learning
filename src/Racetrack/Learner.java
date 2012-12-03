package Racetrack;

import java.util.ArrayList;

import Boundaries.Boundaries;
import ObjectComparisons.ConcreteRacetrackObjects;
import ObjectComparisons.ObjectComparisons;

public abstract class Learner {
	protected Boundaries boundaryLogic;
	protected ObjectComparisons comparer;
	protected MDP mdp;
	protected RaceTrack raceTrack;
	protected double error;
	protected int movementReward = -1;
	protected ArrayList<Q> qValues;
	protected double transitionProb;
	//protected Printer p;

	public Learner(MDP mdp, double error, RaceTrack raceTrack, Boundaries boundaryLogic) {
		this.mdp = mdp;
		this.error = error;
		this.transitionProb = mdp.getTransitionProb();
		this.raceTrack = raceTrack;
		this.boundaryLogic = boundaryLogic;
		comparer = new ConcreteRacetrackObjects();
		//p = new Printer();
	}

	public double reward(RideableState state) {
		double value = -1;
		if (raceTrack.getTile(state.getPosition()) == 'F') {
			value = 1;
		}

		return value;
	}

	public ArrayList<Q> getqValues() {
		return qValues;
	}
	

}
