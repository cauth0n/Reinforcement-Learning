package Racetrack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Printer {
	PrintWriter out;
	File output;

	public Printer(String fName) {
		try {
			output = new File(fName);
			out = new PrintWriter(output);
		} catch (FileNotFoundException e) {

		}
	}

	public void newFile(String name) {
		try {
			out.close();
			output = new File(name);
			out = new PrintWriter(output);
		} catch (FileNotFoundException e) {

		}
	}
	public void closeWriter(){
		out.close();
	}

	public void write(char c) {
		out.print(c);
	}

	public void write(String line) {
		out.print(line);
	}

	public void print(String line) {
		write(line);
		System.out.print(line);
	}

	public void print(char c) {
		write(c);
		System.out.print(c);
	}

	public void println(String line) {
		write(line + "\n");
		System.out.println(line);
	}

	public void println(char c) {
		write(c + "\n");
		System.out.println(c);
	}

	public void printState(RideableState state) {
		println("Racer Position: (" + state.getPosition().getX() + ", " + state.getPosition().getY() + ")");
		println("Racer Velocity: (" + state.getVelocity().getX() + ", " + state.getVelocity().getY() + ")");
		println("Racer's actions: ");
		printActions(state.getActions());
	}

	public void printStates(ArrayList<RideableState> states) {
		int i = 0;
		for (RideableState state : states) {
			println("Action " + i);
			printState(state);
			i++;
		}
	}

	public void printRacer(Racer racer) {
		println("Racer pos: " + racer.getPos().getX() + " " + racer.getPos().getY());
		println("Racer vel: " + racer.getVel().getX() + " " + racer.getVel().getY());
	}

	public void printTrack(RaceTrack raceTrack) {
		for (int i = 0; i < raceTrack.getHeight(); i++) {
			for (int j = 0; j < raceTrack.getWidth(); j++) {
				print(raceTrack.getTile(i, j) + "");
			}
			println("");
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

	public void printQ(Q qValues) {
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
		for (Q qValue : qValues) {
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
