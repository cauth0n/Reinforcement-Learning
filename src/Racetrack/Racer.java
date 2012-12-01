package Racetrack;

public class Racer {

	/**
	 * instance variables -- note that acceleration is not on here; that is because acceleration determines velocity,
	 * and is only used to do so at the accelerate() method.
	 */
	private XYPair position;
	private XYPair velocity;

	public Racer(XYPair position) {
		this.position = position;
		velocity = new XYPair(0, 0);
	}

	public XYPair getPos() {
		return position;
	}

	public XYPair getVel() {
		return velocity;
	}

	public void setVel(XYPair vel) {
		velocity = vel;
	}

	public void setPos(XYPair pos) {
		position = pos;
	}

	public void accelerate(XYPair acceleration) {
		setVel(new XYPair(velocity.getX() + acceleration.getX(), velocity.getY() + acceleration.getY()));
		setPos(new XYPair(position.getX() + velocity.getX(), position.getY() + velocity.getY()));
	}

	public void reset() {
		velocity.setX(0);
		velocity.setY(0);
	}

	public void move(Action action) {
		accelerate(action.getAcceleration());

	}

}
