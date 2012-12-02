package Racetrack;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Printer {

	public Printer() {

	}

	public void print(String line) {
		System.out.print(line);
	}

	public void print(char c) {
		System.out.print(c);
	}

	public void println(String line) {
		System.out.println(line);
	}

	public void println(char c) {
		System.out.println(c);
	}

	public void printState(RideableState state) {
		println("Racer Position: (" + state.getPosition().getX() + ", " + state.getPosition().getY() + ")");
		println("Racer Velocity: (" + state.getVelocity().getX() + ", " + state.getVelocity().getY() + ")");
		println("Racer's actions: ");
		printActions(state.getActions());
		// pause();
	}

	public void printStates(ArrayList<RideableState> states) {
		int i = 0;
		for (RideableState state : states) {
			println("Action " + i);
			printState(state);
			i++;
		}
	}

	public void printTrack(RaceTrack raceTrack) {
		for (int i = 0; i < raceTrack.getWidth(); i++) {
			for (int j = 0; j < raceTrack.getHeight(); j++) {
				System.out.print(raceTrack.getTile(i, j) + "");
			}
			System.out.println("");
		}
	}

	public void printActions(ArrayList<Action> actions) {
		for (int i = 0; i < actions.size(); i++) {
			print(" " + i + ": ");
			printAction(actions.get(i));
		}
	}

	public void printAction(Action action) {
		println("(" + action.getXAcc() + ", " + action.getYAcc() + ")");
	}
	
	public void printQ(Q qValues){
		print("State: ");
		printState(qValues.getState());
		println("Actions: ");
		printAction(qValues.getState().getAction(qValues.getBestActionIndex()));
		print("Best Action so far: ");
		println(qValues.getBestActionIndex() + "");
		print("Value: ");
		println(qValues.getState().getUtility() + "\n");
	}

	public void printQs(ArrayList<Q> qValues) {
		println("Printing Q value pairs");
		for (Q qValue : qValues){
			printQ(qValue);
		}
	}

	public void pause() {
		try {
			Scanner in = new Scanner(System.in);
			println("paused. 1 to continue.");
			int value = 0;
			while (value != 1) {
				value = in.nextInt();
			}
		} catch (InputMismatchException e) {
			println("Wrong input -- continuing");
		}
	}

}
