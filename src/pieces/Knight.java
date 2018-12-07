package pieces;

import model.Piece;
import model.Point;
import view.Board;

/**
 * @author: Havan Patel
 */
public class Knight extends Piece {
	/**
	 * Knight's Constructor
	 * 
	 * @param piecename
	 * @param color
	 */
	public Knight(String piecename, String color) {
		super(piecename, color);
	}

	/**
	 * To see if the move made by knight is valid
	 * 
	 * @param start : get the current position
	 * @param end   : where the piece moves to
	 * @param board : the chess board
	 * @return
	 */
	@Override
	public boolean isMoveAllowed(Point start, Point end, Board board) {
		int row = start.getY() - end.getY();
		int col = start.getX() - end.getX();

		if ((row == 2 && col == 1) || (row == -2 && col == 1) || (row == -2 && col == -1) || (row == 2 && col == -1)
				|| (row == -1 && col == -2) || (row == -1 && col == 2) || (row == 1 && col == -2)
				|| (row == 1 && col == 2)) {

			int x = end.getX();
			int y = end.getY();
			if (!(x <= 7 && x >= 0)) {
				return false;
			}
			if (!(y <= 7 && y >= 0)) {
				return false;
			}

			if (board.piece[end.getX()][end.getY()] == null || !(board.piece[start.getX()][start.getY()].getColor()
					.equalsIgnoreCase(board.piece[end.getX()][end.getY()].getColor()))) {

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
