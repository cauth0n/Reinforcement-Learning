package Racetrack;

public class State {

	private XYPair position;
	private XYPair velocity;
	private double utility = 0;

	public State(XYPair position, XYPair velocity) {
		this.position = position;
		this.velocity = velocity;
	}

	public State(State another) {
		this(another.getPosition(), another.getVelocity());
	}

	public XYPair getPosition() {
		return position;
	}

	public XYPair getVelocity() {
		return velocity;
	}
	public double getUtility() {
		return utility;
	}

	public void setUtility(double utility) {
		this.utility = utility;
	}
}
