package Racetrack;

import java.util.ArrayList;

public class MDP {
	private ArrayList<RideableState> states;

	private final double transitionProb = .8;

	public MDP(ArrayList<RideableState> state) {
		this.states = state;

	}

	public double getTransitionProb() {
		return transitionProb;
	}

	public ArrayList<RideableState> getStates() {
		return states;
	}

}
