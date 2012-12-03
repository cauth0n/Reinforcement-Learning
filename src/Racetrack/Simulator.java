package Racetrack;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import Boundaries.Boundaries;
import Boundaries.ConcreteBoundaries;

public class Simulator {
	private final int lowAcc = -1;
	private final int highAcc = 1;
	private final int lowVelCap = -5;
	private final int highVelCap = 5;
	private final double gamma = .9;
	private final double error = .5;
	private final int iterations = 50;

	private Boundaries boundaryLogic;
	private Printer p;
	private Learner learner;
	private RaceTrack racerlessBoard;
	private ArrayList<XYPair> start;
	private ArrayList<XYPair> finish;
	private MDP mdp;
	private Racer racer;
	private int rows;
	private int cols;
	private long startTime;
	private long stopTime;

	public Simulator(RaceTrack racerLessBoard) {
		this.racerlessBoard = racerLessBoard;
		boundaryLogic = new ConcreteBoundaries(racerlessBoard);
		p = new Printer("ltrack");
		rows = racerLessBoard.getHeight();
		cols = racerLessBoard.getWidth();
		p.printTrack(racerLessBoard);
		init();
	}

	public void init() {
		startFinishInit();
		for (int i = 0; i < 5; i++) {
			startTime = System.currentTimeMillis();
			p.newFile("lTrack" + i);
			constructMDP();
			learn();
			stopTime = System.currentTimeMillis();
			p.println("Total time: " + (stopTime  - startTime));
			p.println("Gamma : " + .8);
			p.println("L track");
			p.println("Iterations: " + iterations);
		}
		p.closeWriter();
	}

	public void putRacer(RaceTrack raceTrack) {
		raceTrack.setRacerPosition(racer.getPos());
		p.println("Racer moved.");
		// p.printTrack(raceTrack);
	}

	public XYPair pickStart() {
		Random rand = new Random();
		return start.get(rand.nextInt(start.size()));
	}

	public void startFinishInit() {
		start = new ArrayList<XYPair>();
		finish = new ArrayList<XYPair>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (racerlessBoard.getTile(i, j) == 'S') {
					start.add(new XYPair(j, i));
				}
				if (racerlessBoard.getTile(i, j) == 'F') {
					finish.add(new XYPair(j, i));
				}
			}
		}
		if (start.isEmpty() || finish.isEmpty()) {
			p.println("No start or finish state found.");
		}
	}

	public void constructMDP() {
		ArrayList<RideableState> allStates = getAllRideableStates();
		p.println("Total number of states: " + allStates.size());
		// p.printStates(allStates);
		mdp = new MDP(allStates);
	}

	public ArrayList<Action> getAllActions(RideableState state) {
		ArrayList<Action> actions = new ArrayList<Action>();
		for (int i = lowAcc; i <= highAcc; i++) {
			if (!(Math.abs(state.getVelocity().getX() + i) > 5)) {
				for (int j = lowAcc; j <= highAcc; j++) {
					if (!(Math.abs(state.getVelocity().getY() + j) > 5)) {
						actions.add(new Action(new XYPair(i, j)));
					}
				}
			}
		}
		return actions;
	}

	public double getEuclidianDistance(int x1, int y1, int x2, int y2) {
		double toRet;
		toRet = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		return toRet;
	}

	public ArrayList<RideableState> getAllRideableStates() {
		ArrayList<RideableState> allStates = new ArrayList<RideableState>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				double minDistFromStart = minDistanceFromStart(j, i);
				if (minDistFromStart == 0) {
					RideableState temp = new RideableState(new XYPair(j, i), new XYPair(0, 0));
					allStates.add(temp);
				} else if (minDistFromStart < highVelCap) {
					for (int k = (highVelCap + lowVelCap) - (int) minDistFromStart; k < (lowVelCap + highVelCap)
							+ (int) (minDistFromStart + 1); k++) {
						for (int l = (highVelCap + lowVelCap) - (int) minDistFromStart; l < (lowVelCap + highVelCap)
								+ (int) (minDistFromStart + 1); l++) {
							if (!(racerlessBoard.getTile(i, j) == '#')) {
								RideableState temp = new RideableState(new XYPair(j, i), new XYPair(k, l));
								allStates.add(temp);
							}
						}
					}

				} else {
					for (int k = lowVelCap; k <= highVelCap; k++) {
						for (int l = lowVelCap; l <= highVelCap; l++) {
							if (!(racerlessBoard.getTile(i, j) == '#')) {
								RideableState temp = new RideableState(new XYPair(j, i), new XYPair(k, l));
								allStates.add(temp);
							}
						}
					}
				}
			}
		}
		allStates = buildActions(allStates);
		return allStates;
	}

	public double minDistanceFromStart(int x, int y) {
		double min = Double.MAX_VALUE;
		for (int i = 0; i < start.size(); i++) {
			double pointDistance = getEuclidianDistance(x, y, start.get(i).getX(), start.get(i).getY());
			if (pointDistance < min) {
				min = pointDistance;
			}
		}
		return min;
	}

	public ArrayList<RideableState> buildActions(ArrayList<RideableState> allStates) {
		ArrayList<RideableState> trimmedStates = allStates;
		for (int i = 0; i < trimmedStates.size(); i++) {
			if (racerlessBoard.getTile(allStates.get(i).getPosition()) != '#') {
				trimmedStates.get(i).setActions(getAllActions(trimmedStates.get(i)));
			}
		}
		return trimmedStates;
	}

	public void runRacer(ArrayList<Q> qValues) {
		char lagptr;
		RaceTrack raceTrack = new RaceTrack(racerlessBoard);
		XYPair start = pickStart();
		racer = new Racer(start, boundaryLogic);
		putRacer(raceTrack);
		p.printTrack(raceTrack);
		try {
			while (racerlessBoard.getTile(racer.getPos()) != 'F') {
				XYPair vel = racer.getVel();
				XYPair pos = racer.getPos();
				int i = 0;
				p.printRacer(racer);
				while (i < qValues.size()) {
					if (learner.comparer.isSamePosition(qValues.get(i).getState(), pos)) {
						if (learner.comparer.isSameVelocity(qValues.get(i).getState(), vel)) {
							break;
						}
					}
					i++;
				}

				Action bestAction = qValues.get(i).getState().getAction(qValues.get(i).getBestActionIndex());
				lagptr = racerlessBoard.getTile(racer.getPos());
				XYPair prevPos = racer.getPos();
				racer.move(bestAction);
				putRacer(raceTrack);// racer goes to x and y spots on track.
				if (!(prevPos.getX() == racer.getPos().getX() && prevPos.getY() == racer.getPos().getY())) {
					raceTrack.setTile(prevPos, lagptr);
				}
				p.printTrack(raceTrack);
				// p.pause();
			}
		} catch (IndexOutOfBoundsException e) {
			p.println("Found an invalid state. Could be a bug regarding a failed acc before finish.");
			p.println("Instead of repeating the excercise, let's go back to start.");
			p.println("Going back to start");
			runRacer(qValues);
		}
	}

	public void learn() {

		learner = new Value_Iteration(mdp, error, gamma, racerlessBoard, boundaryLogic, iterations);
		ArrayList<Q> qValues = learner.getqValues();

		Scanner in = new Scanner(System.in);
		// p.println("Press 1 to run the racer.");
		// if (in.nextInt() == 1) {
		runRacer(qValues);
		// }
	}

}