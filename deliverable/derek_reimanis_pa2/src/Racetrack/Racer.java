package Racetrack;

import java.util.Random;

import Boundaries.Boundaries;

/**
 * Class that defines the framework for a racer.
 * 
 * @author derek.reimanis
 * 
 */
public class Racer {

	/**
	 * instance variables -- note that acceleration is not on here; that is
	 * because acceleration determines velocity, and is only used to do so at
	 * the accelerate() method.
	 */
	private Boundaries boundaryLogic;
	private XYPair position;
	private XYPair velocity;

	/**
	 * Constructor
	 * 
	 * @param position
	 *            racer's starting position.
	 * @param boundaryLogic
	 *            type of boundary logic we want to use (reset or not)
	 */
	public Racer(XYPair position, Boundaries boundaryLogic) {
		this.position = position;
		this.boundaryLogic = boundaryLogic;
		velocity = new XYPair(0, 0);
	}

	/**
	 * get position
	 * 
	 * @return position as an XYPair
	 */
	public XYPair getPos() {
		return position;
	}

	/**
	 * get velocity
	 * 
	 * @return velocity as an XYPair
	 */
	public XYPair getVel() {
		return velocity;
	}

	/**
	 * set velocity
	 * 
	 * @param vel
	 *            new XYPair representing new velocity
	 */
	public void setVel(XYPair vel) {
		velocity = new XYPair(vel);
	}

	/**
	 * set position
	 * 
	 * @param pos
	 *            new XYPair representing new position.
	 */
	public void setPos(XYPair pos) {
		position = new XYPair(pos);
	}

	/**
	 * Accelerate the racer. A random number is picked, then the transitional
	 * model is applied
	 * 
	 * @param acceleration
	 *            acceleration to apply
	 */
	private void accelerate(XYPair acceleration) {
		Random rand = new Random();
		int randVal = rand.nextInt(10);
		if (randVal < 2) {
			RideableState temp;

			temp = boundaryLogic.failedTransisiton(position, velocity);

			setPos(new XYPair(temp.getPosition().getX(), temp.getPosition()
					.getY()));

			if (temp.getVelocity().getX() == 0
					&& temp.getVelocity().getY() == 0) {
				reset();// hit a wall.

			}

		} else {// racer accelerates

			RideableState temp;

			temp = boundaryLogic.transition(position, velocity, acceleration);

			setPos(new XYPair(temp.getPosition().getX(), temp.getPosition()
					.getY()));

			if (temp.getVelocity().getX() == 0
					&& temp.getVelocity().getY() == 0) {
				reset();
			} else {
				setVel(new XYPair(temp.getVelocity().getX(), temp.getVelocity()
						.getY()));
			}

		}
	}

	/**
	 * Reset racer's velocity to zero.
	 */
	public void reset() {
		velocity.setX(0);
		velocity.setY(0);
	}

	/**
	 * move the racer.
	 * 
	 * @param action
	 *            action to apply to racer.
	 */
	public void move(Action action) {
		accelerate(action.getAcceleration());
	}

}
