package Racetrack;

/**
 * Action class, holds a single Action, being an XYPAir
 * 
 * @author derek.reimanis
 *
 */
public class Action {

	private XYPair accelerate;

	public Action(XYPair accelerate) {
		this.accelerate = accelerate;
	}

	/**
	 * Get the XYPair combination of acceleration.
	 * 
	 * @return acceleration as XYPair
	 */
	public XYPair getAcceleration() {
		return accelerate;
	}

	/**
	 * Get the x value of acceleration for this class
	 * 
	 * @return x value of acceleration
	 */
	public int getXAcc() {
		return accelerate.getX();
	}

	/**
	 * Get the y value of acceleration for this class
	 * 
	 * @return y value of acceleration
	 */
	public int getYAcc() {
		return accelerate.getY();
	}

}
