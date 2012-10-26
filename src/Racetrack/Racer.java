package Racetrack;

import java.util.Random;

public class Racer {

	/**
	 * instance variables -- note that acceleration is not on here; that is because acceleration
	 * determines velocity, and is only used to do so at the accelerate() method.
	 */
	private int xPos;
	private int yPos;
	private int xVel;
	private int yVel;

	/**
	 * Constructor. Velocities are initialized to 0.
	 * 
	 * @param xPos
	 *            starting x position (may not be 0)
	 * @param yPos
	 *            starting y position (may not be 0)
	 */
	public Racer(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		xVel = 0;
		yVel = 0;
	}

	/**
	 * Sets the initial position. When the implementation is used to return race car to beginning of
	 * track, input params become 0, 0.
	 * 
	 * Velocity is also reset at this step.
	 * 
	 * 
	 * @param newXPosition
	 *            x position to return car to.
	 * @param newYPosition
	 *            y position to return car to.
	 */
	public void setInit(int newXPosition, int newYPosition) {
		xPos = newXPosition;
		yPos = newYPosition;
		xVel = 0;
		yVel = 0;
	}

	/**
	 * This method checks accelerations coming into this class. The acceleration must be -1, 0, or
	 * 1.
	 * 
	 * This method returns fail if a value coming in does not satisfy that.
	 * 
	 * @param valueToCheck
	 *            acceleration
	 * @return true if the acceleration value is valid.
	 */
	public boolean validAcceleration(int valueToCheck) {
		if (valueToCheck == 1 || valueToCheck == 0 || valueToCheck == -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Void method that takes in acceleration values and applies them to a velocity.
	 * 
	 * The accelerations have an 80% chance of failing, as specified by the assignment.
	 * 
	 * The acceleration cannot being their respective velocities above 5.
	 * 
	 * @param xAcceleration
	 *            acceleration in the x direction
	 * @param yAcceleration
	 *            acceleration in the y direction.
	 */
	public void accelerate(int xAcceleration, int yAcceleration) {
		if (validAcceleration(xAcceleration)) {
			if (validAcceleration(yAcceleration)) {
				Random rand = new Random();
				double r = rand.nextDouble();
				if (r > .2) {
					if (xVel + xAcceleration <= 5 && xVel + xAcceleration >= -5) {
						xVel += xAcceleration;
					}
					if (yVel + yAcceleration <= 5 && yVel + yAcceleration >= -5) {
						yVel += yAcceleration;
					}
					updatePosition();
				}
			}
		}
	}

	/**
	 * Method to update the x and y values on the racetrack. Updates by adding the velocity to thei
	 * respective positions.
	 * 
	 * **Important** --This method is called immediately AFTER the accelerate method. This means no
	 * 'lookahead' checks can be done with acceleration.
	 * 
	 * If lookahead checks need to be done, call this method at the class level.
	 */
	public void updatePosition() {
		xPos += xVel;
		yPos += yVel;
	}

	/**
	 * Getters for x and y velocity and position.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public int getXVel() {
		return xVel;
	}

	public int getYVel() {
		return yVel;
	}

}
