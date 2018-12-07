package pieces;

import model.Piece;
import model.Point;
import view.Board;

/**
 * @author: Havan Patel
 */
public class Queen extends Piece {

	private Rook rook = new Rook(getPiecename(), getColor());
	private Bishop bishop = new Bishop(getPiecename(), getColor());

	/**
	 * Queen's Constructor
	 * 
	 * @param piecename
	 * @param color
	 */
	public Queen(String piecename, String color) {
		super(piecename, color);
	}

	/**
	 * To see if the move made by queen is valid
	 * 
	 * @param start : get the current position
	 * @param end   : where the piece moves to
	 * @param board : the chess board
	 * @return
	 */
	@Override
	public boolean isMoveAllowed(Point start, Point end, Board board) {
		if (start.getX() == end.getX() && start.getY() == end.getY()) {
			return false;
		}
		// queens is mix of rook and bishop
		if (rook.isMoveAllowed(start, end, board) || bishop.isMoveAllowed(start, end, board)) {
			return true;
		}
		return false;
	}

}
