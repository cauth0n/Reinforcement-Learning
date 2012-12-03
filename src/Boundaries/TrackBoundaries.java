package Boundaries;

import Racetrack.Printer;
import Racetrack.RaceTrack;
import Racetrack.RideableState;
import Racetrack.XYPair;

public abstract class TrackBoundaries implements Boundaries {
	protected RaceTrack trackTemplate;
	Printer p = new Printer();

	public TrackBoundaries(RaceTrack raceTrack) {
		this.trackTemplate = raceTrack;
	}

	public RideableState failedTransisiton(XYPair pos, XYPair vel) {
		XYPair position;
		XYPair velocity;
		int xOld = pos.getX();
		int yOld = pos.getY();
		int newXPos = pos.getX() + vel.getX();
		int newYPos = pos.getY() + vel.getY();

		if (smoothXPath(xOld, newXPos, yOld)) {
			if (smoothYPath(yOld, newYPos, newXPos)) {
				position = new XYPair(newXPos, newYPos);// works!
				velocity = new XYPair(vel.getX(), vel.getY());
			} else {// x worked, but y didn't.
				int diff = findYDifference(yOld, newYPos, newXPos);
				position = new XYPair(newXPos, diff);
				velocity = new XYPair(0, 0);
			}
		} else {// x didn't work
			if (smoothYPath(yOld, newYPos, xOld)) {// y works, x doesn't.
				if (smoothXPath(xOld, newXPos, newYPos)) {// special case, y works and THEN x works.
					position = new XYPair(newXPos, newYPos);
					velocity = new XYPair(vel.getX(), vel.getY());
				} else {
					int diff = findXDifference(xOld, newXPos, newYPos);
					position = new XYPair(diff, newYPos);
					velocity = new XYPair(0, 0);
				}
			} else {// neither works.
				int xDiff = findXDifference(xOld, newXPos, yOld);
				// int yDiff = findYDifference(yOld, newYPos, xDiff);
				position = new XYPair(xDiff, yOld);
				velocity = new XYPair(0, 0);
			}
		}

		RideableState newState = new RideableState(position, velocity);
		return newState;
	}

	public RideableState failedTransition(RideableState state) {
		return failedTransisiton(state.getPosition(), state.getVelocity());
	}

	public RideableState transition(XYPair pos, XYPair vel, XYPair accel) {
		XYPair position;
		XYPair velocity;
		int xOld = pos.getX();
		int yOld = pos.getY();

		int newXVel = vel.getX() + accel.getX();
		int newYVel = vel.getY() + accel.getY();
		int newXPos = pos.getX() + newXVel;
		int newYPos = pos.getY() + newYVel;

		if (smoothXPath(xOld, newXPos, yOld)) {
			if (smoothYPath(yOld, newYPos, newXPos)) {
				position = new XYPair(newXPos, newYPos);// works!
				velocity = new XYPair(newXVel, newYVel);

			} else {// x worked, but y didn't.
				int diff = findYDifference(yOld, newYPos, newXPos);
				position = new XYPair(newXPos, diff);
				velocity = new XYPair(0, 0);
			}
		} else {// x didn't work
			if (smoothYPath(yOld, newYPos, xOld)) {// y works, x doesn't.
				if (smoothXPath(xOld, newXPos, newYPos)) {// special case, y works and THEN x works.
					position = new XYPair(newXPos, newYPos);
					velocity = new XYPair(newXVel, newYVel);
				} else {
					int diff = findXDifference(xOld, newXPos, newYPos);
					position = new XYPair(diff, newYPos);
					velocity = new XYPair(0, 0);
				}
			} else {// neither works.
				int xDiff = findXDifference(xOld, newXPos, yOld);
				// int yDiff = findYDifference(yOld, newYPos, xDiff);
				position = new XYPair(xDiff, yOld);
				velocity = new XYPair(0, 0);
			}
		}
		RideableState newState = new RideableState(position, velocity);
		return newState;
	}

	public RideableState transition(RideableState state, int actionIndex) {

		return transition(state.getPosition(), state.getVelocity(), state.getAction(actionIndex).getAcceleration());
	}

	public int findYDifference(int yOld, int yNew, int x) {
		int stop = -1;

		while (stop == -1) {
			if (yNew > yOld) {
				if (trackTemplate.getTile(yOld + 1, x) == '#') {
					stop = yOld;
					break;
				}
				yOld++;
			} else {
				if (trackTemplate.getTile(yOld - 1, x) == '#') {
					stop = yOld;
					break;
				}
				yOld--;
			}
		}
		return stop;
	}

	public int findXDifference(int xOld, int xNew, int y) {
		int stop = -1;

		while (stop == -1) {
			if (xNew > xOld) {
				if (trackTemplate.getTile(y, xOld + 1) == '#') {
					stop = xOld;
					break;
				}
				xOld++;
			} else {
				if (trackTemplate.getTile(y, xOld - 1) == '#') {
					stop = xOld;
					break;
				}
				xOld--;
			}
		}
		return stop;
	}

	public boolean smoothYPath(int yOld, int yNew, int x) {
		boolean check = true;
		try {
			while (yNew != yOld || !check) {
				// p.println(" yNew + yOld " + yOld + " " + yNew + " " + xOld);
				if (yNew > yOld) {
					yOld++;
				} else {
					yOld--;
				}
				if (trackTemplate.getTile(yOld, x) == '#') {
					check = false;
					break;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			check = false;
		}
		return check;
	}

	public boolean smoothXPath(int xOld, int xNew, int yOld) {
		boolean check = true;
		try {
			while (xNew != xOld || !check) {
				// p.println(" xNew + xOld " + xOld + " " + xNew + " " + yOld);
				if (xNew > xOld) {
					xOld++;
				} else {
					xOld--;
				}
				if (trackTemplate.getTile(yOld, xOld) == '#') {
					check = false;
					break;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			check = false;
		}
		return check;
	}
}
