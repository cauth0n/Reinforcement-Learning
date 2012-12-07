package Racetrack;

import java.util.ArrayList;

import Boundaries.Boundaries;
import ObjectComparisons.ConcreteRacetrackObjects;
import ObjectComparisons.ObjectComparisons;

/** 
 * Abstract Learner class
 * 
 * Provides common instances for any reinforcement learning class that may inherit from here.
 * 
 * @author derek.reimanis
 * 
 */
public abstract class Learner {
	protected Boundaries boundaryLogic;
	protected ObjectComparisons comparer;
	protected MDP mdp;
	protected RaceTrack raceTrack;
	protected double error;
	protected int movementReward = -1;
	protected ArrayList<Q> qValues;
	protected double transitionProb;

	// protected Printer p;

	/**
	 * Constructor -- Includes a raceTrack, a markov decision process, some error value corresponding to acceptable
	 * error after iterations, and a definition for what happens when we hit a wall
	 * 
	 * @param mdp
	 *            Markov decision process of states
	 * @param error
	 *            acceptable degree of error after some iteration
	 * @param raceTrack
	 *            track in which the learner will move.
	 * @param boundaryLogic
	 *            what happens when we hit a wall.
	 */
	public Learner(MDP mdp, double error, RaceTrack raceTrack, Boundaries boundaryLogic) {
		this.mdp = mdp;
		this.error = error;
		this.transitionProb = mdp.getTransitionProb();
		this.raceTrack = raceTrack;
		this.boundaryLogic = boundaryLogic;
		comparer = new ConcreteRacetrackObjects();
		// p = new Printer();
	}

	/**
	 * Reward for any state
	 * 
	 * @param state
	 *            current state of which we want the valu of
	 * 
	 * @return 1 if finish state, else -1
	 */
	public double reward(RideableState state) {
		double value = -1;
		if (raceTrack.getTile(state.getPosition()) == 'F') {
			value = 1;
		}

		return value;
	}

	/**
	 * Getter for the Q(s,a) pairs
	 * 
	 * @return Q(s,a)
	 */
	public ArrayList<Q> getqValues() {
		return qValues;
	}

	public ArrayList<Q> getQ() {
		ArrayList<Q> getQValues = new ArrayList<Q>(mdp.getStates().size());
		for (int i = 0; i < mdp.getStates().size(); i++) {
			getQValues.add(new Q(mdp.getStates().get(i)));
		}
		return getQValues;
	}
}
