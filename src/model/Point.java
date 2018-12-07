package model;

/**
 * @author: Havan Patel
 */
public class Point {
	public int x;
	public int y;

	/**
	 * initialize the point class
	 * 
	 * @param x : x position
	 * @param y : y position
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * set x and y to something else
	 * 
	 * @param x
	 * @param y
	 */
	public void setXandY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * print the x and y
	 */
	@Override
	public String toString() {
		return "[x=" + x + ",y=" + y + "]";
	}
}
