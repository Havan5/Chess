package pieces;

import model.Piece;
import model.Point;
import view.Board;

/**
 * @author: Havan Patel
 */
public class Rook extends Piece {

	public boolean rookMoved = false;
	
	/**
	 * Rook's Constructor 
	 * @param piecename
	 * @param color
	 */
	public Rook(String piecename, String color) {
		super(piecename, color);
	}

	/**
	 * To see if the move made by rook is valid
	 * 
	 * @param start : get the current position
	 * @param end   : where the piece moves to
	 * @param board : the chess board
	 * @return
	 */
	@Override
	public boolean isMoveAllowed(Point start, Point end, Board board) {
		int row = Math.abs(start.getY() - end.getY());
		int col = Math.abs(start.getX() - end.getX());
		String c = board.piece[start.getX()][start.getY()].getColor();

		// left
		if (col == 0) {
			if (start.getX() == end.getX() && start.getY() > end.getY()) {
				for (int i = 1; i <= row; i++) {
					if (board.piece[start.getX()][start.getY() - i] != null) {
						if (!(board.piece[start.getX()][start.getY() - i].getColor().equalsIgnoreCase(c))) {
							if (start.getX() == end.getX() && start.getY() - i == end.getY()) {
								rookMoved = true;
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
				}
				rookMoved = true;
				return true;
			} else {
				for (int i = 1; i <= row; i++) {
					if (board.piece[start.getX()][start.getY() + i] != null) {
						if (!(board.piece[start.getX()][start.getY() + i].getColor().equalsIgnoreCase(c))) {
							if (start.getX() == end.getX() && start.getY() + i == end.getY()) {
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
				}
				rookMoved = true;
				return true;
			}
		}

		// up
		if (row == 0) {
			if (start.getX() > end.getX() && start.getY() == end.getY()) {
				for (int i = 1; i <= col; i++) {
					if (board.piece[start.getX() - i][start.getY()] != null) {
						if (!(board.piece[start.getX() - i][start.getY()].getColor().equalsIgnoreCase(c))) {
							if (start.getX() - i == end.getX() && start.getY() == end.getY()) {
								rookMoved = true;
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
				}
				rookMoved = true;
				return true;
			} else {
				for (int i = 1; i <= col; i++) {
					if (board.piece[start.getX() + i][start.getY()] != null) {
						if (!(board.piece[start.getX() + i][start.getY()].getColor().equalsIgnoreCase(c))) {
							if (start.getX() + i == end.getX() && start.getY() == end.getY()) {
								rookMoved = true;
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
				}
				return true;
			}
		}
		rookMoved = true;
		return false;
	}
}
