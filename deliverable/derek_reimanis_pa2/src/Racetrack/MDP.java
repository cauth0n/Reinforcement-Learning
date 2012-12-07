package Racetrack;

import java.util.ArrayList;

/**
 * Class that defines a Markov Decision Process.
 * States and a transitional probability are encapsulated here!
 * Note that actions for each state are stored in the states themselves!
 * 
 * @author derek.reimanis
 *
 */
public class MDP {
	
	private ArrayList<RideableState> states;
	private final double transitionProb = .8;

	/**
	 * Constructor -- take in an arraylist of states.
	 * 
	 * @param state pre-existing arraylist of states
	 */
	public MDP(ArrayList<RideableState> state) {
		this.states = state;
	}

	/** 
	 * get transitional probability of moving successfully
	 * @return probability of moving successfully
	 */
	public double getTransitionProb() {
		return transitionProb;
	}

	/**
	 * get the states in this mdp
	 * @return
	 */
	public ArrayList<RideableState> getStates() {
		return states;
	}

}
