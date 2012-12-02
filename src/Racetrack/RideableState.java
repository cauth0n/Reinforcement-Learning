package Racetrack;

import java.util.ArrayList;

public class RideableState extends State {

	private ArrayList<Action> actionsInState;
	private XYPair velocity;

	public RideableState(XYPair position, XYPair velocity) {
		super(position);
		this.velocity = velocity;
		actionsInState = new ArrayList<Action>();
	}

	public RideableState(RideableState another) {
		this(another.getPosition(), another.getVelocity());
	}

	public void setActions(ArrayList<Action> actions) {
		actionsInState = actions;
	}

	public void addAction(Action a) {
		actionsInState.add(a);
	}

	public Action getAction(int i) {
		return actionsInState.get(i);
	}

	public ArrayList<Action> getActions() {
		return actionsInState;
	}

	public XYPair getVelocity() {
		return velocity;
	}

	public void setUtility(double utility) {
		this.utility = utility;
	}

}
