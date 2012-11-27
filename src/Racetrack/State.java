package Racetrack;

public class State {

	private double utility;
	private XYPair position;
	private XYPair velocity;

	public State(XYPair position, XYPair velocity) {
		this.position = position;
		this.velocity = velocity;
	}

	public State(State another) {
		this(another.getPosition(), another.getVelocity());
	}

	public double getUtility() {
		return utility;
	}

	public void setUtility(double in) {
		utility = in;
	}

	public void resetPosition(XYPair newPosition) {
		this.position = newPosition;
	}

	public void restVelocity() {
		this.velocity = new XYPair(0, 0);
	}

	public XYPair getPosition() {
		return position;
	}

	public XYPair getVelocity() {
		return velocity;
	}

	public int getXPosition() {
		return position.getX();
	}

	public int getYPosition() {
		return position.getY();
	}

	public int getXVelocity() {
		return velocity.getX();
	}

	public int getYVelocity() {
		return velocity.getY();
	}
}
