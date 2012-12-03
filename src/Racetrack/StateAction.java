package Racetrack;

public class StateAction {
	private State state;
	private Action action;
	private double stateActionVal;

	public StateAction(State state, Action a) {
		this.state = state;
		this.action = a;
	}

	public void setStateActionVal(double in) {
		stateActionVal = in;
	}

	public double getStateActionVal() {
		return stateActionVal;
	}

	public State getState() {
		return state;
	}

	public Action getAction() {
		return action;
	}

}
