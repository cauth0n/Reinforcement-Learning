package Racetrack;

public abstract class State {
	protected XYPair position;
	protected double utility = 0;
	
	
	public State(XYPair position){
		this.position = position;
	}

	public XYPair getPosition() {
		return position;
	}

	public double getUtility() {
		return utility;
	}

}
