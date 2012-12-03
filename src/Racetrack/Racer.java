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
	private Printer p;

	public Racer(XYPair position, Boundaries boundaryLogic) {
		this.position = position;
		this.boundaryLogic = boundaryLogic;
		p = new Printer();
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

	private void accelerate(XYPair acceleration) {
		Random rand = new Random();
		int randVal = rand.nextInt(10);
		if (randVal < 2) {
			p.println("Racer failed, before set: " + position.getX() + " " + position.getY());
			RideableState temp = boundaryLogic.failedTransisiton(position, velocity);

			// racer does not accelerate.
			setPos(new XYPair(temp.getPosition().getX(), temp.getPosition().getY()));
			p.println("Racer failed, after set: " + position.getX() + " " + position.getY());

			if (temp.getVelocity().getX() == 0 && temp.getVelocity().getY() == 0) {
				reset();// hit a wall.

			}
		} else {// racer accelerates
			p.println("Racer success, before set: " + position.getX() + " " + position.getY());
			RideableState temp = boundaryLogic.transition(position, velocity, acceleration);
			p.println("Racer success, position after set: " + temp.getPosition().getX() + " " + temp.getPosition().getY());
			p.println("Racer success, velocity after set: " + temp.getVelocity().getX() + " " + temp.getVelocity().getY());
			p.println("Racer success, acceleration after set: " + acceleration.getX() + " " + acceleration.getY());
			
			setPos(new XYPair(temp.getPosition().getX(), temp.getPosition().getY()));
			//p.println("Racer success, after set: " + position.getX() + " " + position.getY());
			if (temp.getVelocity().getX() == 0 && temp.getVelocity().getY() == 0) {
				reset();
			} else {
				setVel(new XYPair(temp.getVelocity().getX() + acceleration.getX(), velocity.getY()
						+ acceleration.getY()));
			}
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
