package Racetrack;

import java.util.ArrayList;

public class Q {
	private State state;
	private ArrayList<Action> actions;
	private double utility = 0;
	private int bestActionIndex;

	public Q(State state, ArrayList<Action> actions) {
		this.state = state;
		this.actions = actions;
	}

	public void addAction(Action a) {
		actions.add(a);
	}

	public double getUtility() {
		return utility;
	}

	public void setUtility(double utility) {
		this.utility = utility;
	}

	public State getState() {
		return state;
	}
	
	public int getBestActionIndex(){
		return bestActionIndex;
	}
	public void setBestActionIndex(int updatedActionIndex){
		bestActionIndex = updatedActionIndex;
	}

	public ArrayList<Action> getActions() {
		return actions;
	}

}
