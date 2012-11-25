package Racetrack;

import java.util.ArrayList;

public class MDP {
	private ArrayList<State> state;
	private final double transitionProb = .8;

	public MDP(ArrayList<State> state) {
		this.state = state;
	}

	public double getTransitionProb() {
		return transitionProb;
	}

	public ArrayList<State> getStates() {
		return state;
	}

}
