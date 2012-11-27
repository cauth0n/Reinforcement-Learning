package Racetrack;

import java.util.ArrayList;

public class MDP {
	private ArrayList<State> states;
	private ArrayList<Action> actions;
	private final double transitionProb = .8;

	public MDP(ArrayList<State> state, ArrayList<Action> actions) {
		this.states = state;
		this.actions = actions;
	}

	public ArrayList<Action> getActions() {
		return actions;
	}

	public double getTransitionProb() {
		return transitionProb;
	}

	public ArrayList<State> getStates() {
		return states;
	}

}
