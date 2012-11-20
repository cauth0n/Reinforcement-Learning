package Racetrack;

public class Simulator {
	private Learner leaner;
	private Racer racer;
	
	public Simulator(){
		leaner = new Value_Iteration();
		racer = new Racer(0, 0);
	}
}
