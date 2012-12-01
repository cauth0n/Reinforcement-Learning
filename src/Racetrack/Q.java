package Racetrack;

import java.util.ArrayList;

public class Q {
	private State state;
	private ArrayList<Action> actions;
	private int bestActionIndex = -1;	//used to return argmax over actions

	public Q(State state, ArrayList<Action> actions) {
		this.state = state;
		this.actions = actions;
	}

	public void addAction(Action a) {
		actions.add(a);
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
