package pieces;

import model.Piece;
import model.Point;
import view.Board;

/**
 * @author: Havan Patel
 */
public class Pawn extends Piece {

	boolean pawnFirstMove = true;
	boolean pawnDoubleJump = false;

	/**
	 * Pawn's Constructor
	 * 
	 * @param piecename
	 * @param color
	 */
	public Pawn(String piecename, String color) {
		super(piecename, color);
	}

	/**
	 * To see if the move made by pawn is valid
	 * 
	 * @param start : get the current position
	 * @param end   : where the piece moves to
	 * @param board : the chess board
	 * @return
	 */
	@Override
	public boolean isMoveAllowed(Point start, Point end, Board board) {
		if ((end.getX() <= 7 && end.getX() >= 0) && (end.getY() <= 7 && end.getY() >= 0)) {
			if (super.getColor().equalsIgnoreCase("white")) {
				if (pawnFirstMove) {
					if (isPawnsFirstMove(start, end, 1)) {
						int isDoubleMove = start.getX() - end.getX();
						// check this condition. using end instead start
						if (isPawnDoubleJump(isDoubleMove, start, board, 1)) {
							pawnFirstMove = false;
							pawnDoubleJump = true;
							return true;
						} else {
							if (isPawnOneJump(isDoubleMove, start, board, 1)) {
								pawnFirstMove = false;
								pawnDoubleJump = false;
								return true;
							} else {
								return false;
							}
						}
					} else if (end.getX() > 3 && end.getX() < 6 && end.getY() == start.getY() - 1
							&& end.getX() == start.getX() - 1 && board.piece[end.getX()][end.getY()] != null
							&& board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("black")) {
						pawnDoubleJump = false;
						pawnFirstMove = false;
						return true;
					} else if (end.getX() > 3 && end.getX() < 6 && end.getY() == start.getY() + 1
							&& end.getX() == start.getX() - 1 && board.piece[end.getX()][end.getY()] != null
							&& board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("black")) {
						pawnDoubleJump = false;
						pawnFirstMove = false;
						return true;
					} else {
						pawnFirstMove = true;
						return false;
					}
				} else {
					if (start.getY() == end.getY()) {
						if (start.getX() - 1 == end.getX() && board.piece[end.getX()][end.getY()] == null) {
							return true;
						} else {
							return false;
						}
					} else {
						if (isPassant(start, end, board, 1)) {
							return true;
						} else {
							if (end.getY() < start.getY()) {
								if (canMoveDiagonal(start, end, board, 1)) {
									return true;
								} else {
									return false;
								}
							} else {
								if (canMoveDiagonal(start, end, board, 2)) {
									return true;
								} else {
									return false;
								}
							}
						}
					}
				}

			} else {
				if (pawnFirstMove) {
					if (isPawnsFirstMove(start, end, 2)) {
						int isDoubleMove = end.getX() - start.getX();
						// check this condition. using end instead start
						if (isPawnDoubleJump(isDoubleMove, start, board, 2)) {
							pawnFirstMove = false;
							pawnDoubleJump = true;
							return true;
						} else {
							if (isPawnOneJump(isDoubleMove, start, board, 2)) {
								pawnFirstMove = false;
								pawnDoubleJump = false;
								return true;
							} else {
								return false;
							}
						}
					} else if (end.getX() <= 3 && end.getX() > 1 && end.getY() == start.getY() - 1
							&& end.getX() == start.getX() + 1 && board.piece[end.getX()][end.getY()] != null
							&& board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("white")) {
						pawnDoubleJump = false;
						pawnFirstMove = false;
						return true;
					} else if (end.getX() <= 3 && end.getX() > 1 && end.getY() == start.getY() + 1
							&& end.getX() == start.getX() + 1 && board.piece[end.getX()][end.getY()] != null
							&& board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("white")) {
						pawnDoubleJump = false;
						pawnFirstMove = false;
						return true;
					} else {
						pawnFirstMove = true;
						return false;
					}
				} else {
					if (start.getY() == end.getY()) {
						if (start.getX() + 1 == end.getX() && board.piece[end.getX()][end.getY()] == null) {
							return true;
						} else {
							return false;
						}
					} else {
						if (isPassant(start, end, board, 2)) {
							return true;
						} else {
							if (end.getY() < start.getY()) {
								if (canMoveDiagonal2(start, end, board, 1)) {
									return true;
								} else {
									return false;
								}
							} else {
								if (canMoveDiagonal2(start, end, board, 2)) {
									return true;
								} else {
									return false;
								}
							}
						}
					}
				}
			}
		} else {
			return false;
		}
	}

	/**
	 * can the pawn move diagonal
	 * 
	 * @param start
	 * @param end
	 * @param board
	 * @param n     : which players it is
	 * @return
	 */
	public boolean canMoveDiagonal(Point start, Point end, Board board, int n) {
		if (n == 1) {
			if (board.piece[end.getX()][end.getY()] != null
					&& board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("black")
					&& start.getX() - 1 == end.getX() && start.getY() - 1 == end.getY()) {
				return true;
			}
		} else {
			if (board.piece[end.getX()][end.getY()] != null
					&& board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("black")
					&& start.getX() - 1 == end.getX() && start.getY() + 1 == end.getY()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * can the pawn move diagonal
	 * 
	 * @param start
	 * @param end
	 * @param board
	 * @param n     : which players it is
	 * @return
	 */
	public boolean canMoveDiagonal2(Point start, Point end, Board board, int n) {
		if (n == 1) {
			if (board.piece[end.getX()][end.getY()] != null
					&& board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("white")
					&& start.getX() + 1 == end.getX() && start.getY() - 1 == end.getY()) {
				return true;
			}
		} else {
			if (board.piece[end.getX()][end.getY()] != null
					&& board.piece[end.getX()][end.getY()].getColor().equalsIgnoreCase("white")
					&& start.getX() + 1 == end.getX() && start.getY() + 1 == end.getY()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * is the piece capturing in enPassant position
	 * 
	 * @param start
	 * @param end
	 * @param board
	 * @param n
	 * @return
	 */
	public boolean isPassant(Point start, Point end, Board board, int n) {
		if (n == 1) {
			if (board.piece[end.getX()][end.getY()] == null && start.getX() == 3
					&& (end.getY() + 1 == start.getY() || end.getY() - 1 == start.getY())) {
				int x = end.getX() + 1;
				int y = end.getY();
				if (!(x <= 7 && x >= 0)) {
					return false;
				}
				if (!(y <= 7 && y >= 0)) {
					return false;
				}
				if (board.piece[end.getX() + 1][end.getY()] != null
						&& board.piece[end.getX() + 1][end.getY()].getColor().equalsIgnoreCase("black")
						&& hadJumped(end.getX() + 1, end.getY(), board)
						&& ((end.getX() + 1 == start.getX() && end.getY() + 1 == start.getY())
								|| (end.getX() + 1 == start.getX() && end.getY() - 1 == start.getY()))) {
					board.piece[end.getX() + 1][end.getY()] = null;
					return true;
				} else {
					return false;
				}
			}
		} else {
			if (board.piece[end.getX()][end.getY()] == null && start.getX() == 4
					&& (end.getY() + 1 == start.getY() || end.getY() - 1 == start.getY())) {
				int x = end.getX() - 1;
				int y = end.getY();
				if (!(x <= 7 && x >= 0)) {
					return false;
				}
				if (!(y <= 7 && y >= 0)) {
					return false;
				}
				if (board.piece[end.getX() - 1][end.getY()] != null
						&& board.piece[end.getX() - 1][end.getY()].getColor().equalsIgnoreCase("white")
						&& hadJumped(end.getX() - 1, end.getY(), board)
						&& ((end.getX() - 1 == start.getX() && end.getY() + 1 == start.getY())
								|| (end.getX() - 1 == start.getX() && end.getY() - 1 == start.getY()))) {
					board.piece[end.getX() - 1][end.getY()] = null;
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * has the pawn moved to 2 spots
	 * 
	 * @param row
	 * @param col
	 * @param board
	 * @return
	 */
	private boolean hadJumped(int row, int col, Board board) {
		if (board.piece[row][col] instanceof Pawn) {
			Pawn pawn = (Pawn) board.piece[row][col];
			return pawn.pawnDoubleJump;
		}
		return false;
	}

	/**
	 * did pawn moved to only one spot
	 * 
	 * @param isDoubleMove
	 * @param start
	 * @param board
	 * @param n
	 * @return
	 */
	public boolean isPawnOneJump(int isDoubleMove, Point start, Board board, int n) {
		if (n == 1) {
			if (isDoubleMove == 1 && board.piece[start.getX() - 1][start.getY()] == null) {
				return true;
			}
		} else {
			if (isDoubleMove == 1 && board.piece[start.getX() + 1][start.getY()] == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * did pawn move to 2 spot up
	 * 
	 * @param isdoubleJump
	 * @param start
	 * @param board
	 * @param n
	 * @return
	 */
	public boolean isPawnDoubleJump(int isdoubleJump, Point start, Board board, int n) {
		if (n == 1) {
			if (isdoubleJump == 2 && board.piece[start.getX() - 1][start.getY()] == null
					&& board.piece[start.getX() - 2][start.getY()] == null) {
				return true;
			}
		} else {
			if (isdoubleJump == 2 && board.piece[start.getX() + 1][start.getY()] == null
					&& board.piece[start.getX() + 2][start.getY()] == null) {
				return true;
			}

		}
		return false;
	}

	/**
	 * is this pawn's first move
	 * 
	 * @param start
	 * @param end
	 * @param n
	 * @return
	 */
	boolean isPawnsFirstMove(Point start, Point end, int n) {
		if (n == 1) {
			if (end.getX() > 3 && end.getX() < 6 && isPwanGoingStraight(start, end)) {
				return true;
			}
		} else {
			if (end.getX() < 4 && end.getX() > 1 && isPwanGoingStraight(start, end)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * is pawn moving in straight line
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean isPwanGoingStraight(Point start, Point end) {
		if (start.getY() == end.getY()) {
			return true;
		}
		return false;
	}

}
