package Racetrack;

public class XYPair {
	private int x;
	private int y;

	public XYPair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public XYPair(XYPair another) {
		this(another.getX(), another.getY());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
