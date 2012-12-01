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

	public void printState(State state) {
		println("Racer Position: (" + state.getPosition().getX() + ", "
				+ state.getPosition().getY() + ")");
		println("Racer Velocity: (" + state.getVelocity().getX() + ", "
				+ state.getVelocity().getY() + ")");
	}

	public void printStates(ArrayList<State> states) {
		for (State state : states) {
			printState(state);
		}
	}

	public void printTrack(Race_Track raceTrack) {
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

	public void printQ(ArrayList<Q> qValues){
		println("Printing Q value pairs");
		for (int i = 0; i < qValues.size(); i++){
			print("State: ");
			printState(qValues.get(i).getState());
			println("Actions: ");
			printActions(qValues.get(i).getActions());
			print("Best Action so far: ");
			println(qValues.get(i).getBestActionIndex() + "");
			print("Value: ");
			println(qValues.get(i).getState().getUtility() + "\n");
		}
	}
	
	public void pause(){
		try{
		Scanner in = new Scanner(System.in);
		println("paused. 1 to continue.");
		int value = 0;
		while (value != 1){
			value = in.nextInt();
		}
		}catch(InputMismatchException e){
			println("Wrong input -- continuing");
		}
	}
	
}
