package Racetrack;

public class StateAction {
	private RideableState state;
	private Action action;
	private double stateActionVal;

	public StateAction(StateAction another) {
		this(another.getState(), another.getAction());
	}

	public StateAction(RideableState state) {
		this.state = state;
		action = new Action(new XYPair(0, 0));
	}

	public StateAction(RideableState state, Action a) {
		this.state = state;
		this.action = a;
	}

	public void setState(RideableState state) {
		this.state = state;
	}

	public void setStateActionVal(double in) {
		stateActionVal = in;
	}

	public void setAction(Action a) {
		this.action = a;
	}

	public double getStateActionVal() {
		return stateActionVal;
	}

	public RideableState getState() {
		return state;
	}

	public Action getAction() {
		return action;
	}

}
