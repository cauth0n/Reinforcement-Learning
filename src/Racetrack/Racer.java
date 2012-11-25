package Racetrack;

public class Racer {

	/**
	 * instance variables -- note that acceleration is not on here; that is
	 * because acceleration determines velocity, and is only used to do so at
	 * the accelerate() method.
	 */
	private XYPair position;
	private XYPair velocity;

	public Racer(XYPair positions) {
		this.position = position;
		velocity = new XYPair(0, 0);
	}

	public int getXPos() {
		return position.getX();
	}

	public int getYPos() {
		return position.getY();
	}

	public int getXVel() {
		return velocity.getX();
	}

	public int getYVel() {
		return velocity.getY();
	}

	public void accelerate(XYPair acceleration) {
		velocity.setX(getXVel() + acceleration.getX());
		velocity.setY(getYVel() + acceleration.getY());
		position.setX(getXPos() + getXVel());
		position.setY(getYPos() + getYVel());
	}

	public void reset() {
		velocity.setX(0);
		velocity.setY(0);
	}

	public void move(Action action) {
		accelerate(action.getAcceleration());

	}

}
