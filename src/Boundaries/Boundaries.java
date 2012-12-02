package Boundaries;

import Racetrack.Action;
import Racetrack.RideableState;
import Racetrack.XYPair;

public interface Boundaries {


	public RideableState failedTransition(RideableState state);
	
	public RideableState failedTransisiton(XYPair pos, XYPair vel);

	public RideableState transition(RideableState state, int actionIndex);
	
	public RideableState transition(XYPair pos, XYPair vel, XYPair accel);

	public int findYDifference(int yOld, int yNew, int x);

	public int findXDifference(int xOld, int xNew, int y);

	public boolean smoothYPath(int yOld, int yNew, int xOld);

	public boolean smoothXPath(int xOld, int xNew, int yOld);
}
