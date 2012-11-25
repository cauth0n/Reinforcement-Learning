package Racetrack;

public class Action {

	private XYPair accelerate;

	public Action(XYPair accelerate) {
		this.accelerate = accelerate;
	}

	public XYPair getAcceleration() {
		return accelerate;
	}

	public int getXAcc() {
		return accelerate.getX();
	}

	public int getYAcc() {
		return accelerate.getY();
	}

}
