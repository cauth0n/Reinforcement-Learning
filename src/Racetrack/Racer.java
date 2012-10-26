package Racetrack;

import java.util.Random;

public class Racer {

	private int xPos;
	private int yPos;
	private int xVel;
	private int yVel;

	public Racer(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		xVel = 0;
		yVel = 0;
	}

	public void setInit(int newXPosition, int newYPosition) {
		xPos = newXPosition;
		yPos = newYPosition;
		xVel = 0;
		yVel = 0;
	}

	public boolean validAcceleration(int valueToCheck) {
		if (valueToCheck == 1 || valueToCheck == 0 || valueToCheck == -1){
			return true;
		}else{
			return false;
		}
	}

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

	public void updatePosition() {
		xPos += xVel;
		yPos += yVel;
	}

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
