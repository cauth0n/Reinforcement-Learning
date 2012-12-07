package Racetrack;

import java.util.Random;

import Boundaries.Boundaries;

public class Racer {

	/**
	 * instance variables -- note that acceleration is not on here; that is because acceleration determines velocity,
	 * and is only used to do so at the accelerate() method.
	 */
	private Boundaries boundaryLogic;
	private XYPair position;
	private XYPair velocity;

	// private Printer p;

	public Racer(XYPair position, Boundaries boundaryLogic) {
		this.position = position;
		this.boundaryLogic = boundaryLogic;

		// p = new Printer();
		velocity = new XYPair(0, 0);
	}

	public XYPair getPos() {
		return position;
	}

	public XYPair getVel() {
		return velocity;
	}

	public void setVel(XYPair vel) {
		velocity = new XYPair(vel);
	}

	public void setPos(XYPair pos) {
		position = new XYPair(pos);
	}

	private void accelerate(XYPair acceleration) {
		Random rand = new Random();
		int randVal = rand.nextInt(10);
		if (randVal < 2) {
			RideableState temp;

			// if (startOver) {
			temp = boundaryLogic.failedTransisiton(position, velocity);
			// } else {
			// temp = boundaryLogic.failedTransisiton(position, velocity);
			// }
			// racer does not accelerate.
			setPos(new XYPair(temp.getPosition().getX(), temp.getPosition().getY()));

			if (temp.getVelocity().getX() == 0 && temp.getVelocity().getY() == 0) {
				reset();// hit a wall.

			}
			// p.println("Racer failed to accelerate. Velocity is now: (" + velocity.getX() + ", " + velocity.getY() +
			// ")");
			// p.println("							   Position moves to: (" + position.getX() + ", " + position.getY() + ")");

		} else {// racer accelerates

			RideableState temp;

			// if (startOver) {
			temp = boundaryLogic.transition(position, velocity, acceleration);
			// } else {
			// temp = boundaryLogic.transition(position, velocity, acceleration);
			// }
			setPos(new XYPair(temp.getPosition().getX(), temp.getPosition().getY()));

			if (temp.getVelocity().getX() == 0 && temp.getVelocity().getY() == 0) {
				reset();
			} else {
				setVel(new XYPair(temp.getVelocity().getX(), temp.getVelocity().getY()));
			}
			// p.println("Racer accelerated! Acceleration is: (" + acceleration.getX() + ", " + acceleration.getY() +
			// ")");
			// p.println("					  Velocity is now: (" + velocity.getX() + ", " + velocity.getY() + ")");
			// p.println("					  Position moves to: (" + position.getX() + ", " + position.getY() + ")");
		}
	}

	public void reset() {
		velocity.setX(0);
		velocity.setY(0);
	}

	public void move(Action action) {
		accelerate(action.getAcceleration());
	}

}
