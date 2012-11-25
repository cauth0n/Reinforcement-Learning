package Racetrack;

public class Value_Iteration extends Learner {
	private double delta = 0;
	private double gamma;

	public Value_Iteration(MDP mdp, int error, double gamma) {
		super(mdp, error);
		this.gamma = gamma;
		logic();
	}

	public void logic() {
		double transitionProb = mdp.getTransitionProb();
		for (int iterator = 0; iterator < 5; iterator++) {
			do {
				for (int i = 0; i < mdp.getStates().size() - 1; i++) {
					double oldUtility = mdp.getStates().get(i).getUtility();
					double nextUtility = oldUtility;
					double maxActionExpectedValue = 0;
					int bestAction = Integer.MIN_VALUE;

					if (Math.abs(nextUtility - oldUtility) > delta) {
						delta = nextUtility - oldUtility;
					}
				}
			} while (delta < error * (1 - gamma) / gamma);
			printScores();
		}
	}
	public void printScores(){
		
	}

}
