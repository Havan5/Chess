package pieces;

import model.Piece;
import model.Point;
import view.Board;

/**
 * @author: Havan Patel
 */
public class King extends Piece {

	public boolean canCastle = false;
	public boolean kingMoved = false;
	public boolean isInCheck = false;

	public Rook rook = new Rook(getPiecename(), getColor());

	/**
	 * King's Constructor 
	 * @param piecename
	 * @param color
	 */
	public King(String piecename, String color) {
		super(piecename, color);
	}

	/**
	 * To see if the move made by king is valid
	 * 
	 * @param start : get the current position
	 * @param end   : where the piece moves to
	 * @param board : the chess board
	 * @return
	 */
	@Override
	public boolean isMoveAllowed(Point start, Point end, Board board) {
		if ((end.getX() <= 7 && end.getX() >= 0) && (end.getY() <= 7 && end.getY() >= 0)) {
			if (board.piece[start.getX()][start.getY()].getColor().equals("white")) {
				if (start.getX() - 1 == end.getX() && start.getY() + 1 == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("black"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() - 1 == end.getX() && start.getY() - 1 == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("black"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() + 1 == end.getX() && start.getY() - 1 == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("black"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() + 1 == end.getX() && start.getY() + 1 == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("black"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() - 1 == end.getX() && start.getY() == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("black"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() + 1 == end.getX() && start.getY() == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("black"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() == end.getX() && start.getY() - 1 == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("black"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() == end.getX() && start.getY() + 1 == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("black"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() == 7 && start.getX() == end.getX() && end.getX() == 7 && !kingMoved
						&& !rook.rookMoved && start.getY() == end.getY() - 2 && (end.getY() - start.getY() == 2)
						&& board.piece[start.getX()][start.getY() + 1] == null
						&& board.piece[start.getX()][start.getY() + 2] == null && board.piece[7][7] instanceof Rook) {
					canCastle = true;
					return true;
				} else if (start.getX() == 7 && start.getX() == end.getX() && end.getX() == 7 && !kingMoved
						&& !rook.rookMoved && start.getY() == end.getY() + 2 && (start.getY() - end.getY() == 2)
						&& board.piece[start.getX()][start.getY() - 1] == null
						&& board.piece[start.getX()][start.getY() - 2] == null
						&& board.piece[start.getX()][start.getY() - 3] == null && board.piece[7][0] instanceof Rook) {
					canCastle = true;
					return true;
				} else {
					return false;
				}

			} else {
				if (start.getX() - 1 == end.getX() && start.getY() + 1 == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("white"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() - 1 == end.getX() && start.getY() - 1 == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("white"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() + 1 == end.getX() && start.getY() - 1 == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("white"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() + 1 == end.getX() && start.getY() + 1 == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("white"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() - 1 == end.getX() && start.getY() == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("white"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() + 1 == end.getX() && start.getY() == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("white"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() == end.getX() && start.getY() - 1 == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("white"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() == end.getX() && start.getY() + 1 == end.getY()
						&& (board.piece[end.getX()][end.getY()] == null
								|| board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("white"))) {
					kingMoved = true;
					return true;
				} else if (start.getX() == 0 && start.getX() == end.getX() && end.getX() == 0 && !kingMoved
						&& !rook.rookMoved && start.getY() == end.getY() - 2 && (end.getY() - start.getY() == 2)
						&& board.piece[start.getX()][start.getY() + 1] == null
						&& board.piece[start.getX()][start.getY() + 2] == null && board.piece[0][7] instanceof Rook) {
					canCastle = true;
					return true;
				} else if (start.getX() == 0 && start.getX() == end.getX() && end.getX() == 0 && !kingMoved
						&& !rook.rookMoved && start.getY() == end.getY() + 2 && (start.getY() - end.getY() == 2)
						&& board.piece[start.getX()][start.getY() - 1] == null
						&& board.piece[start.getX()][start.getY() - 2] == null
						&& board.piece[start.getX()][start.getY() - 3] == null && board.piece[0][0] instanceof Rook) {
					canCastle = true;
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

}
