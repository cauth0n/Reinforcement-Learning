package Racetrack;

/**
 * Class that defines an XY point in a Cartesian plane.
 * 
 * @author derek.reimanis
 * 
 */
public class XYPair {
	private int x;
	private int y;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            x location
	 * @param y
	 *            y location
	 */
	public XYPair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Cloneable constructor
	 * 
	 * @param another
	 *            XY points to clone
	 */
	public XYPair(XYPair another) {
		this(another.getX(), another.getY());
	}

	/**
	 * get the x point
	 * 
	 * @return x point
	 */
	public int getX() {
		return x;
	}

	/**
	 * set the x point
	 * 
	 * @param x
	 *            the value to set x to
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * get the y point
	 * 
	 * @return y point
	 */
	public int getY() {
		return y;
	}

	/**
	 * set the y point
	 * 
	 * @param y
	 *            the value to set y to
	 */
	public void setY(int y) {
		this.y = y;
	}

}
