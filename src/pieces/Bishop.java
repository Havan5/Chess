package pieces;

import model.Piece;
import model.Point;
import view.Board;

/**
 * @author: Havan Patel
 */
public class Bishop extends Piece {
	/**
	 * Bishop's Constructor 
	 * @param piecename
	 * @param color
	 */
	public Bishop(String piecename, String color) {
		super(piecename, color);
	}


	/**
	 * To see if the move made by Bishop is valid
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
		if (start.getX() == end.getX() && start.getX() == end.getX()) {
			return false;
		}

		// up left
		if (row == col) {
			if (start.getX() > end.getX() && start.getY() > end.getY()) {
				for (int i = 1; i <= col; i++) {
					if (board.piece[start.getX() - i][start.getY() - i] != null) {
						if (!(board.piece[start.getX() - i][start.getY() - i].getColor().equalsIgnoreCase(c))) {
							if (start.getX() - i == end.getX() && start.getY() - i == end.getY()) {
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

		// up right
		if (row == col) {
			if (start.getX() > end.getX() && start.getY() < end.getY()) {
				for (int i = 1; i <= col; i++) {
					if (board.piece[start.getX() - i][start.getY() + i] != null) {
						if (!(board.piece[start.getX() - i][start.getY() + i].getColor().equalsIgnoreCase(c))) {
							if (start.getX() - i == end.getX() && start.getY() + i == end.getY()) {
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
		// down left
		if (row == col) {
			if (start.getX() < end.getX() && start.getY() > end.getY()) {
				for (int i = 1; i <= col; i++) {
					if (board.piece[start.getX() + i][start.getY() - i] != null) {
						if (!(board.piece[start.getX() + i][start.getY() - i].getColor().equalsIgnoreCase(c))) {
							if (start.getX() + i == end.getX() && start.getY() - i == end.getY()) {
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
		if (row == col) {
			if (start.getX() < end.getX() && start.getY() < end.getY()) {
				for (int i = 1; i <= col; i++) {
					if (board.piece[start.getX() + i][start.getY() + i] != null) {
						if (!(board.piece[start.getX() + i][start.getY() + i].getColor().equalsIgnoreCase(c))) {
							if (start.getX() + i == end.getX() && start.getY() + i == end.getY()) {
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
		return false;
	}
}