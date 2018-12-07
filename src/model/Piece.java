package model;

import view.Board;

/**
 * @author: Havan Patel
 */
public abstract class Piece {

	protected String color;
	protected String piecename;

	/**
	 * 
	 * @param color : which piece it is "pawn, king , queen, etc."
	 * @param piecename : color of that piece "black or white"
	 */
	public Piece(String piecename, String color) {
		this.piecename = piecename;
		this.color = color;
	}
	
	/**
	 * To see if the move made is valid for the chosen piece
	 * @param start : get the current position
	 * @param end : where the piece moves to
	 * @param board : the chess board
	 * @return
	 */
	public abstract boolean isMoveAllowed(Point start, Point end, Board board);

	/**
	 * 
	 * @return color of the piece
	 */
	public String getColor() {
		return color;
	}

	/**
	 * 
	 * @return name of the piece 
	 */
	public String getPiecename() {
		return piecename;
	}

	/**
	 * get the piece name
	 */
	public String toString() {
		return piecename;
	}
}
