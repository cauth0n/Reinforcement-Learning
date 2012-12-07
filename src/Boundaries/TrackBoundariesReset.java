package Boundaries;

import Racetrack.Action;
import Racetrack.RaceTrack;
import Racetrack.RideableState;
import Racetrack.XYPair;

public abstract class TrackBoundariesReset implements Boundaries {

	protected RaceTrack trackTemplate;
	protected XYPair reset;

	/**
	 * Constructor -- holds the race track specific to this implementation.
	 * 
	 * @param raceTrack
	 */
	public TrackBoundariesReset(RaceTrack raceTrack, XYPair reset) {
		this.trackTemplate = raceTrack;
		this.reset = reset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Boundaries.Boundaries#failedTransisiton(Racetrack.XYPair, Racetrack.XYPair)
	 */
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
				// XYPair val = findYDifference(yOld, newYPos, newXPos);
				position = new XYPair(reset);
				velocity = new XYPair(0, 0);
			}
		} else {// x didn't work
			if (smoothYPath(yOld, newYPos, xOld)) {// y works, x doesn't.
				if (smoothXPath(xOld, newXPos, newYPos)) {// special case, y
															// works and THEN x
															// works.
					position = new XYPair(newXPos, newYPos);
					velocity = new XYPair(vel.getX(), vel.getY());
				} else {
					// XYPair val = findXDifference(xOld, newXPos, newYPos);
					position = new XYPair(reset);
					velocity = new XYPair(0, 0);
				}
			} else {// neither works.
				// XYPair val = findXDifference(xOld, newXPos, yOld);
				// int yDiff = findYDifference(yOld, newYPos, xDiff);
				position = new XYPair(reset);
				velocity = new XYPair(0, 0);
			}
		}

		RideableState newState = new RideableState(position, velocity);
		return newState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Boundaries.Boundaries#failedTransition(Racetrack.RideableState)
	 */
	public RideableState failedTransition(RideableState state) {
		return failedTransisiton(state.getPosition(), state.getVelocity());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Boundaries.Boundaries#transition(Racetrack.XYPair, Racetrack.XYPair, Racetrack.XYPair)
	 */
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
				// XYPair val = findYDifference(yOld, newYPos, newXPos);
				position = new XYPair(reset);
				velocity = new XYPair(0, 0);
			}
		} else {// x didn't work
			if (smoothYPath(yOld, newYPos, xOld)) {// y works, x doesn't.
				if (smoothXPath(xOld, newXPos, newYPos)) {// special case, y
															// works and THEN x
															// works.
					position = new XYPair(newXPos, newYPos);
					velocity = new XYPair(newXVel, newYVel);
				} else {
					// XYPair val = findXDifference(xOld, newXPos, newYPos);
					position = new XYPair(reset);
					velocity = new XYPair(0, 0);
				}
			} else {// neither works.
				// XYPair val = findXDifference(xOld, newXPos, yOld);
				// int yDiff = findYDifference(yOld, newYPos, xDiff);
				position = new XYPair(reset);
				velocity = new XYPair(0, 0);
			}
		}
		RideableState newState = new RideableState(position, velocity);
		return newState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Boundaries.Boundaries#transition(Racetrack.RideableState, int)
	 */
	public RideableState transition(RideableState state, int actionIndex) {

		return transition(state.getPosition(), state.getVelocity(), state.getAction(actionIndex).getAcceleration());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Boundaries.Boundaries#transition(Racetrack.RideableState, Racetrack.Action)
	 */
	public RideableState transition(RideableState state, Action action) {
		return transition(state.getPosition(), state.getVelocity(), action.getAcceleration());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Boundaries.Boundaries#findYDifference(int, int, int)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see Boundaries.Boundaries#findXDifference(int, int, int)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see Boundaries.Boundaries#smoothYPath(int, int, int)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see Boundaries.Boundaries#smoothXPath(int, int, int)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see Boundaries.Boundaries#isFinished(Racetrack.RideableState)
	 */
	public boolean isFinished(RideableState state) {
		boolean check = false;
		if (trackTemplate.getTile(state.getPosition()) == 'F') {
			check = true;
		}
		return check;
	}

}
