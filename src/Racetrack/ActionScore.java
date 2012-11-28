package Racetrack;

public class ActionScore {
	private double score;
	private int actionNum;
	
	public ActionScore(double one, int two){
		this.score = one;
		this.actionNum = two;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double one) {
		this.score = one;
	}

	public int getActionNum() {
		return actionNum;
	}

	public void SetActionScore(int two) {
		this.actionNum = two;
	}
	

}
