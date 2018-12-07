package chess;

import java.util.Scanner;

import model.Player;
import model.Point;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;
import view.Board;

/**
 * @author: Havan Patel
 */

public class Game {

	private boolean playerTurn1 = true;
	private boolean playerTurn2 = false;
	private boolean win = false;

	private boolean promotePawn = false;
	private boolean moveNotAllowed = false;
	private boolean castle = true;

	private boolean gameDraw = false;
	private boolean calledDraw = false;
	private boolean finalDraw = false;

	private Player p1, p2;
	private Point StartPoint1 = new Point(0, 0);
	private Point start, end, kingsPosition, newStartPoint;
	private King king = null, king2 = null;

	/**
	 * setting up the game
	 */
	public Game() {
		Board board = new Board();

		p1 = new Player("white", board);
		p2 = new Player("black", board);
		p1.setUpPieces();
		p2.setUpPieces();

		board.render();

		kingsPosition = new Point(0, 0);
		newStartPoint = new Point(0, 0);
		start(p1, p2, board);
	}

	/**
	 * checks if the input is valid
	 * 
	 * @param p1    : player1
	 * @param p2    : player2
	 * @param board : current board
	 * @return
	 */
	public boolean checkUserInput(Player p1, Player p2, Board board) {
		Scanner sc = new Scanner(System.in);
		if (playerTurn1) {
			System.out.print(p1.getName() + "'s move: ");
		} else {
			System.out.print(p2.getName() + "'s move: ");
		}
		String input = sc.nextLine();

		if (resign(p1, p2, input)) {
			return false;
		}

		if (input.equalsIgnoreCase("draw")) {
			if (playerTurn1 && p2.draw) {
				gameDraw = true;
				return false;
			} else if (playerTurn2 && p1.draw) {
				gameDraw = true;
				return false;
			} else {
				return false;
			}
		}

		if (input.length() <= 4) {
			return false;
		}

		if (movePiece(input, board)) {
			return true;
		}
		return false;
	}

	/**
	 * Check to see if piece can be moved
	 * 
	 * @param input : input by user
	 * @param board : current board
	 * @return
	 */
	public boolean movePiece(String input, Board board) {
		start = getPointPoisition(input.substring(0, 2));
		end = getPointPoisition(input.substring(3, 5));

		if (start == null || end == null) {
			return false;
		}

		if (board.piece[start.getX()][start.getY()] == null) {
			return false;
		}

		if (input.length() == 5 || input.length() == 7) {
			if (board.piece[start.getX()][start.getY()] instanceof Pawn) {
				Pawn p = (Pawn) board.piece[start.getX()][start.getY()];
				if (p.getColor().equalsIgnoreCase("white") && start.getX() == 1 && end.getX() == 0) {
					if (start.getX() == end.getX() + 1 && start.getY() == end.getY()
							&& board.piece[end.getX()][end.getY()] == null && start.getX() == 1 && end.getX() == 0) {
						if (input.length() == 5) {
							promotePawn(start, end, board, 'Q', "white");
							promotePawn = true;
							return false;
						} else if (input.length() == 7 && (input.substring(6, 7).equalsIgnoreCase("N")
								|| input.substring(6, 7).equalsIgnoreCase("R")
								|| input.substring(6, 7).equalsIgnoreCase("Q")
								|| input.substring(6, 7).equalsIgnoreCase("B"))) {
							String c = input.toUpperCase();
							promotePawn(start, end, board, c.charAt(6), "white");
							promotePawn = true;
							return false;
						} else {
							promotePawn = false;
							return true;
						}
					} else if (start.getX() == end.getX() + 1 && start.getY() == end.getY() + 1
							&& board.piece[end.getX()][end.getY()] != null && start.getX() == 1 && end.getX() == 0) {
						if (input.length() == 5) {
							promotePawn(start, end, board, 'Q', "white");
							promotePawn = true;
							return false;
						} else if ((input.length() == 7 && (input.substring(6, 7).equalsIgnoreCase("N")
								|| input.substring(6, 7).equalsIgnoreCase("R")
								|| input.substring(6, 7).equalsIgnoreCase("Q")
								|| input.substring(6, 7).equalsIgnoreCase("B")))) {
							String c = input.toUpperCase();
							promotePawn(start, end, board, c.charAt(6), "white");
							promotePawn = true;
							return false;
						} else {
							promotePawn = false;
							return true;
						}
					} else if (start.getX() == end.getX() + 1 && start.getY() == end.getY() - 1
							&& board.piece[end.getX()][end.getY()] != null && start.getX() == 1 && end.getX() == 0) {
						if (input.length() == 5) {
							promotePawn(start, end, board, 'Q', "white");
							promotePawn = true;
							return false;
						} else if ((input.length() == 7 && (input.substring(6, 7).equalsIgnoreCase("N")
								|| input.substring(6, 7).equalsIgnoreCase("R")
								|| input.substring(6, 7).equalsIgnoreCase("Q")
								|| input.substring(6, 7).equalsIgnoreCase("B")))) {
							String c = input.toUpperCase();
							promotePawn(start, end, board, c.charAt(6), "white");
							promotePawn = true;
							return false;
						} else {
							promotePawn = false;
							return true;
						}
					} else {
						promotePawn = false;
						return true;
					}
				} else if (p.getColor().equalsIgnoreCase("black") && start.getX() == 6 && end.getX() == 7) {
					if (start.getX() == end.getX() - 1 && start.getY() == end.getY()
							&& board.piece[end.getX()][end.getY()] == null && start.getX() == 6 && end.getX() == 7) {
						if (input.length() == 5) {
							promotePawn(start, end, board, 'Q', "black");
							promotePawn = true;
							return false;
						} else if (input.length() == 7 && (input.substring(6, 7).equalsIgnoreCase("N")
								|| input.substring(6, 7).equalsIgnoreCase("R")
								|| input.substring(6, 7).equalsIgnoreCase("Q")
								|| input.substring(6, 7).equalsIgnoreCase("B"))) {
							String c = input.toUpperCase();
							promotePawn(start, end, board, c.charAt(6), "black");
							promotePawn = true;
							return false;
						} else {
							promotePawn = false;
							return true;
						}
					} else if (start.getX() == end.getX() - 1 && start.getY() == end.getY() + 1
							&& board.piece[end.getX()][end.getY()] != null && start.getX() == 6 && end.getX() == 7) {
						if (input.length() == 5) {
							promotePawn(start, end, board, 'Q', "black");
							promotePawn = true;
							return false;
						} else if ((input.length() == 7 && (input.substring(6, 7).equalsIgnoreCase("N")
								|| input.substring(6, 7).equalsIgnoreCase("R")
								|| input.substring(6, 7).equalsIgnoreCase("Q")
								|| input.substring(6, 7).equalsIgnoreCase("B")))) {
							String c = input.toUpperCase();
							promotePawn(start, end, board, c.charAt(6), "black");
							promotePawn = true;
							return false;
						} else {
							promotePawn = false;
							return true;
						}
					} else if (start.getX() == end.getX() - 1 && start.getY() == end.getY() - 1
							&& board.piece[end.getX()][end.getY()] != null && start.getX() == 6 && end.getX() == 7) {
						if (input.length() == 5) {
							promotePawn(start, end, board, 'Q', "black");
							promotePawn = true;
							return false;
						} else if ((input.length() == 7 && (input.substring(6, 7).equalsIgnoreCase("N")
								|| input.substring(6, 7).equalsIgnoreCase("R")
								|| input.substring(6, 7).equalsIgnoreCase("Q")
								|| input.substring(6, 7).equalsIgnoreCase("B")))) {
							String c = input.toUpperCase();
							promotePawn(start, end, board, c.charAt(6), "black");
							promotePawn = true;
							return false;
						} else {
							promotePawn = false;
							return true;
						}
					} else {
						promotePawn = false;
						return true;
					}
				}
			} else {
				promotePawn = false;
				return true;
			}
		}

		if (draw(input, board)) {
			return true;
		} else if (input.length() > 5 && input.length() < 11) {
			return false;
		} else if ((p1.draw || p2.draw) && input.length() > 5 && input.length() == 11
				&& input.substring(6, 11).equalsIgnoreCase("draw?")) {
			return false;
		}

		return true;
	}

	/**
	 * is the game draw
	 * 
	 * @param input : user input
	 * @param board : current board
	 * @return
	 */
	public boolean draw(String input, Board board) {
		if (input.length() > 5 && input.length() == 11 && !p1.draw && !p2.draw) {
			if (playerTurn1 && input.substring(6, 11).equalsIgnoreCase("draw?")) {
				p1.draw = true;
				calledDraw = true;
				return true;
			} else if (playerTurn2 && input.substring(6, 11).equalsIgnoreCase("draw?")) {
				p2.draw = true;
				calledDraw = true;
				return true;
			}
		}
		return false;
	}

	/**
	 * convert the letter to points
	 * 
	 * @param input : user input
	 * @return point
	 */
	private Point getPointPoisition(String input) {
		char x, y;
		x = input.charAt(0);
		y = input.charAt(1);
		switch (x) {
		case 'a':
			x = '0';
			break;
		case 'b':
			x = '1';
			break;
		case 'c':
			x = '2';
			break;
		case 'd':
			x = '3';
			break;
		case 'e':
			x = '4';
			break;
		case 'f':
			x = '5';
			break;
		case 'g':
			x = '6';
			break;
		case 'h':
			x = '7';
			break;
		default:
			return null;
		}
		if ((8 - Character.getNumericValue(y)) < 0 || (8 - Character.getNumericValue(y)) > 7) {
			return null;
		}
		return new Point(8 - Character.getNumericValue(y), Character.getNumericValue(x));
	}

	/**
	 * did player resigned
	 * 
	 * @param p1 : player1
	 * @param p2 : player2
	 * @param s  : input
	 * @return
	 */
	public boolean resign(Player p1, Player p2, String s) {
		if (s.equalsIgnoreCase("resign")) {
			if (playerTurn1) {
				win = true;
				p2.won = true;
				return true;
			} else if (playerTurn2) {
				win = true;
				p1.won = true;
				return true;
			}
		}
		return false;
	}

	/**
	 * start the game
	 * 
	 * @param p1 : player1
	 * @param p2 : player2
	 * @param s  : input
	 * @return
	 */
	public void start(Player p1, Player p2, Board board) {
		while (!win) {
			if (checkUserInput(p1, p2, board)) {
				if (playerTurn1 && canCastle(playerTurn1, board)) {
					playerTurn1 = !playerTurn1;
					playerTurn2 = !playerTurn2;
					board.render();
				} else if (playerTurn2 && canCastle(playerTurn2, board)) {
					playerTurn1 = !playerTurn1;
					playerTurn2 = !playerTurn2;
					board.render();
				} else if (((playerTurn2 && board.piece[start.getX()][start.getY()].getColor().equals(p2.getName()))
						|| (playerTurn1 && board.piece[start.getX()][start.getY()].getColor().equals(p1.getName())))
						&& castle && board.piece[start.getX()][start.getY()] != null
						&& board.piece[start.getX()][start.getY()].isMoveAllowed(start, end, board)) {
					board.piece[end.getX()][end.getY()] = board.piece[start.getX()][start.getY()];
					board.piece[start.x][start.y] = null;
					board.render();
					playerTurn1 = !playerTurn1;
					playerTurn2 = !playerTurn2;
					if (calledDraw) {
						finalDraw = true;
					}
				} else {
					moveNotAllowed = true;
					castle = true;
					if (calledDraw && moveNotAllowed) {
						if ((p1.draw || p2.draw) && finalDraw == false) {
							p1.draw = false;
							p2.draw = false;
							calledDraw = false;
						}
					}
					System.out.println("illegal move, try again\n");
				}
			} else if (promotePawn) {
				playerTurn1 = !playerTurn1;
				playerTurn2 = !playerTurn2;
				promotePawn = false;
			} else if (gameDraw) {
				win = true;
			} else if (p1.won) {
				System.out.println("white player won");
			} else if (p2.won) {
				System.out.println("black player won");
			} else {
				castle = true;
				System.out.println("illegal move, try again\n");
			}

			if (gameDraw && win) {
				System.out.println("draw");
			}

			if (isKingInCheck("bK", board)) {
				System.out.println("Check\n");
				castle = true;
				king = (King) board.piece[kingsPosition.getX()][kingsPosition.getY()];
				king.isInCheck = true;
				if (playerTurn1 && king.isInCheck) {
					System.out.println("Checkmate");
					System.out.println("white player win");
					win = true;
				} else if (checkMate("bK", board)) {
					System.out.println("Checkmate");
					System.out.println("white player win");
					win = true;
				}
			} else {
				if (!(king == null)) {
					king.isInCheck = false;
				}
			}
			if (isKingInCheck("wK", board)) {
				System.out.println("Check\n");
				castle = true;
				king2 = (King) board.piece[kingsPosition.getX()][kingsPosition.getY()];
				king2.isInCheck = true;
				if (playerTurn2 && king2.isInCheck) {
					System.out.println("Checkmate");
					System.out.println("black player win");
					win = true;
				} else if (checkMate("wK", board)) {
					System.out.println("Checkmate");
					System.out.println("black player win");
					win = true;
				}
			} else {
				if (!(king2 == null)) {
					king2.isInCheck = false;
				}
			}

		}

//		if (gameDraw && win) {
//			System.out.println("draw");
//		} else {
//			if (p1.won) {
//				System.out.println("white player won");
//			} else if (p2.won) {
//				System.out.println("black player won");
//			}
//		}
	}

	/**
	 * is the king in checkmate
	 * 
	 * @param king  : which king
	 * @param board : current board
	 * @return
	 */
	public boolean checkMate(String king, Board board) {
		int[] trackCheckMatePosition = { 1, 1, 1, 1, 1, 1, 1, 1 };

		currentPositionOfKing(king, board);
		String k = null;

		Point ul = new Point(kingsPosition.getX() - 1, kingsPosition.getY() - 1);
		Point u = new Point(kingsPosition.getX() - 1, kingsPosition.getY());
		Point ur = new Point(kingsPosition.getX() - 1, kingsPosition.getY() + 1);
		Point r = new Point(kingsPosition.getX(), kingsPosition.getY() + 1);
		Point dr = new Point(kingsPosition.getX() + 1, kingsPosition.getY() + 1);
		Point d = new Point(kingsPosition.getX() + 1, kingsPosition.getY());
		Point dl = new Point(kingsPosition.getX() + 1, kingsPosition.getY() - 1);
		Point l = new Point(kingsPosition.getX(), kingsPosition.getY() - 1);

		if (board.piece[kingsPosition.getX()][kingsPosition.getY()].isMoveAllowed(kingsPosition, ul, board)) {
			if (isKingInCheckmatePosition(ul, king, board, 1)) {
				trackCheckMatePosition[0] = 1;
			} else {
				trackCheckMatePosition[0] = 0;
			}

			if (king.equals("wK")) {
				k = "bK";
			} else {
				k = "wK";
			}

			// can king's people save me
			if ((isKingInCheckmatePosition(ul, k, board, 2) || isKingInCheckmatePosition(StartPoint1, k, board, 2))
					|| (isKingInCheckmatePosition(ul, k, board, 3) && !isKingInCheckmatePosition(ul, king, board, 1))) {
				trackCheckMatePosition[0] = 0;
			} else {
				trackCheckMatePosition[0] = 1;
			}

		}
		if (board.piece[kingsPosition.getX()][kingsPosition.getY()].isMoveAllowed(kingsPosition, u, board)) {
			if (isKingInCheckmatePosition(u, king, board, 1)) {
				trackCheckMatePosition[1] = 1;
			} else {
				trackCheckMatePosition[1] = 0;
			}
			if (king.equals("wK")) {
				k = "bK";
			} else {
				k = "wK";
			}

			// can king's people save me
			if ((isKingInCheckmatePosition(u, k, board, 2) || isKingInCheckmatePosition(StartPoint1, k, board, 2))
					|| (isKingInCheckmatePosition(u, k, board, 3) && !isKingInCheckmatePosition(u, king, board, 1))) {
				trackCheckMatePosition[1] = 0;
			} else {
				trackCheckMatePosition[1] = 1;
			}
		}
		if (board.piece[kingsPosition.getX()][kingsPosition.getY()].isMoveAllowed(kingsPosition, ur, board)) {
			if (isKingInCheckmatePosition(ur, king, board, 1)) {
				trackCheckMatePosition[2] = 1;
			} else {
				trackCheckMatePosition[2] = 0;
			}
			if (king.equals("wK")) {
				k = "bK";
			} else {
				k = "wK";
			}

			// can king's people save me
			if ((isKingInCheckmatePosition(ur, k, board, 2) || isKingInCheckmatePosition(StartPoint1, k, board, 2))
					|| (isKingInCheckmatePosition(ur, k, board, 3) && !isKingInCheckmatePosition(ur, king, board, 1))) {
				trackCheckMatePosition[2] = 0;
			} else {
				trackCheckMatePosition[2] = 1;
			}

		}
		if (board.piece[kingsPosition.getX()][kingsPosition.getY()].isMoveAllowed(kingsPosition, r, board)) {
			if (isKingInCheckmatePosition(r, king, board, 1)) {
				trackCheckMatePosition[3] = 1;
			} else {
				trackCheckMatePosition[3] = 0;
			}
			if (king.equals("wK")) {
				k = "bK";
			} else {
				k = "wK";
			}

			// can king's people save me
			if ((isKingInCheckmatePosition(r, k, board, 2) || isKingInCheckmatePosition(StartPoint1, k, board, 2))
					|| (isKingInCheckmatePosition(r, k, board, 3) && !isKingInCheckmatePosition(r, king, board, 1))) {
				trackCheckMatePosition[3] = 0;
			} else {
				trackCheckMatePosition[3] = 1;
			}

		}
		if (board.piece[kingsPosition.getX()][kingsPosition.getY()].isMoveAllowed(kingsPosition, dr, board)) {
			if (isKingInCheckmatePosition(dr, king, board, 1)) {
				trackCheckMatePosition[4] = 1;
			} else {
				trackCheckMatePosition[4] = 0;
			}
			if (king.equals("wK")) {
				k = "bK";
			} else {
				k = "wK";
			}

			// can king's people save me
			if ((isKingInCheckmatePosition(dr, k, board, 2) || isKingInCheckmatePosition(StartPoint1, k, board, 2))
					|| (isKingInCheckmatePosition(dr, k, board, 3) && !isKingInCheckmatePosition(dr, king, board, 1))) {
				trackCheckMatePosition[4] = 0;
			} else {
				trackCheckMatePosition[4] = 1;
			}

		}
		if (board.piece[kingsPosition.getX()][kingsPosition.getY()].isMoveAllowed(kingsPosition, d, board)) {
			if (isKingInCheckmatePosition(d, king, board, 1)) {
				trackCheckMatePosition[5] = 1;
			} else {
				trackCheckMatePosition[5] = 0;
			}
			if (king.equals("wK")) {
				k = "bK";
			} else {
				k = "wK";
			}

			// can king's people save me
			if ((isKingInCheckmatePosition(d, k, board, 2) || isKingInCheckmatePosition(StartPoint1, k, board, 2))
					|| (isKingInCheckmatePosition(d, k, board, 3) && !isKingInCheckmatePosition(d, king, board, 1))) {
				trackCheckMatePosition[5] = 0;
			} else {
				trackCheckMatePosition[5] = 1;
			}

		}
		if (board.piece[kingsPosition.getX()][kingsPosition.getY()].isMoveAllowed(kingsPosition, dl, board)) {
			if (isKingInCheckmatePosition(dl, king, board, 1)) {
				trackCheckMatePosition[6] = 1;
			} else {
				trackCheckMatePosition[6] = 0;
			}
			if (king.equals("wK")) {
				k = "bK";
			} else {
				k = "wK";
			}

			// can king's people save me
			if ((isKingInCheckmatePosition(dl, k, board, 2) || isKingInCheckmatePosition(StartPoint1, k, board, 2))
					|| (isKingInCheckmatePosition(dl, k, board, 3) && !isKingInCheckmatePosition(dl, king, board, 1))) {
				trackCheckMatePosition[6] = 0;
			} else {
				trackCheckMatePosition[6] = 1;
			}

		}
		if (board.piece[kingsPosition.getX()][kingsPosition.getY()].isMoveAllowed(kingsPosition, l, board)) {
			if (isKingInCheckmatePosition(l, king, board, 1)) {
				trackCheckMatePosition[7] = 1;
			} else {
				trackCheckMatePosition[7] = 0;
			}
			if (king.equals("wK")) {
				k = "bK";
			} else {
				k = "wK";
			}

			// can king's people save me
			if ((isKingInCheckmatePosition(l, k, board, 2) || isKingInCheckmatePosition(StartPoint1, k, board, 2))
					|| (isKingInCheckmatePosition(l, k, board, 3) && !isKingInCheckmatePosition(l, king, board, 1))) {
				trackCheckMatePosition[7] = 0;
			} else {
				trackCheckMatePosition[7] = 1;
			}

		}

		for (int i = 0; i < trackCheckMatePosition.length; i++) {
			if (trackCheckMatePosition[i] == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * returns the positions in which the king would be in checkmate position if it
	 * were to move
	 * 
	 * @param p     : where it wants to go
	 * @param king  : which king
	 * @param board : current board
	 * @param n     : check
	 * @return
	 */
	public boolean isKingInCheckmatePosition(Point p, String king, Board board, int n) {
		String pieceColor = null;
		if (king.equals("bK")) {
			if (n == 1) {
				currentPositionOfKing("bK", board);
			}
			pieceColor = "white";
		} else {
			if (n == 1) {
				currentPositionOfKing("wK", board);
			}
			pieceColor = "black";
		}
		Point StartPoint = new Point(0, 0);
		if (n == 3) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board.piece[i][j] != null) {
						if (board.piece[i][j].getColor().equals(pieceColor)) {
							StartPoint.setXandY(i, j);
							if (board.piece[i][j] instanceof King) {
								if (board.piece[i][j].isMoveAllowed(StartPoint, p, board)) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		if (n == 2) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board.piece[i][j] != null) {
						if (board.piece[i][j].getColor().equals(pieceColor)) {
							StartPoint.setXandY(i, j);
							if (!(board.piece[i][j] instanceof King)) {
								if (board.piece[i][j].isMoveAllowed(StartPoint, p, board)) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		if (n == 1) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board.piece[i][j] != null) {
						if (board.piece[i][j].getColor().equalsIgnoreCase(pieceColor)) {
							StartPoint1.setXandY(i, j);
							if (board.piece[i][j].isMoveAllowed(StartPoint1, p, board)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * return the position of current kings
	 * 
	 * @param king
	 * @param board
	 */
	public void currentPositionOfKing(String king, Board board) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.piece[i][j] != null) {
					if (board.piece[i][j].getPiecename().equals(king)) {
						kingsPosition.setXandY(i, j);
					}
				}
			}
		}
	}

	/**
	 * is king in check
	 * 
	 * @param king
	 * @param board
	 * @return
	 */
	public boolean isKingInCheck(String king, Board board) {
		String pieceColor = null;
		if (king.equals("bK")) {
			currentPositionOfKing("bK", board);
			pieceColor = "white";
		} else {
			currentPositionOfKing("wK", board);
			pieceColor = "black";
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.piece[i][j] != null) {
					if (board.piece[i][j].getColor().equals(pieceColor)) {
						newStartPoint.setXandY(i, j);
						if (board.piece[i][j].isMoveAllowed(newStartPoint, kingsPosition, board))
							return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * can you castle the king
	 * 
	 * @param turn  : whose turn
	 * @param board : current board
	 * @return
	 */
	public boolean canCastle(boolean turn, Board board) {
		if (turn == playerTurn1) {
			if (board.piece[start.getX()][start.getY()] instanceof King) {
				if (start.getX() == end.getX() && start.getY() + 1 == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() == end.getX() && start.getY() - 1 == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() - 1 == end.getX() && start.getY() - 1 == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() - 1 == end.getX() && start.getY() == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() - 1 == end.getX() && start.getY() + 1 == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() + 1 == end.getX() && start.getY() - 1 == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() + 1 == end.getX() && start.getY() == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() + 1 == end.getX() && start.getY() + 1 == end.getY()) {
					castle = true;
					return false;
				}
				if (board.piece[start.getX()][start.getY()].isMoveAllowed(start, end, board)) {
					King k = (King) board.piece[start.getX()][start.getY()];
					if (isKingInCheck("wK", board)) {
						castle = false;
						return false;
					}
					if (k.canCastle && !k.kingMoved && k.getColor().equals("white")) {
						if (start.getY() > end.getY() && board.piece[7][0] instanceof Rook) {
							if (isKingInCheck("wK", board)) {
								castle = false;
								return false;
							}
							board.piece[end.getX()][end.getY()] = board.piece[start.getX()][start.getY()];
							board.piece[start.getX()][start.getY()] = null;
							board.piece[end.getX()][end.getY() + 1] = board.piece[7][0];
							board.piece[7][0] = null;
							k.kingMoved = true;
							return true;
						} else if (start.getY() < end.getY() && board.piece[7][7] instanceof Rook) {
							if (isKingInCheck("wK", board)) {
								castle = false;
								return false;
							}
							board.piece[end.getX()][end.getY()] = board.piece[start.getX()][start.getY()];
							board.piece[start.getX()][start.getY()] = null;
							board.piece[end.getX()][end.getY() - 1] = board.piece[7][7];
							board.piece[7][7] = null;
							k.kingMoved = true;
							return true;
						} else {
							return false;
						}
					}
				}
			}
		} else {
			if (board.piece[start.getX()][start.getY()] instanceof King) {
				if (start.getX() == end.getX() && start.getY() + 1 == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() == end.getX() && start.getY() - 1 == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() - 1 == end.getX() && start.getY() - 1 == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() - 1 == end.getX() && start.getY() == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() - 1 == end.getX() && start.getY() + 1 == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() + 1 == end.getX() && start.getY() - 1 == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() + 1 == end.getX() && start.getY() == end.getY()) {
					castle = true;
					return false;
				} else if (start.getX() + 1 == end.getX() && start.getY() + 1 == end.getY()) {
					castle = true;
					return false;
				}
				if (board.piece[start.getX()][start.getY()].isMoveAllowed(start, end, board)) {
					King k = (King) board.piece[start.getX()][start.getY()];
					if (isKingInCheck("bK", board)) {
						castle = false;
						return false;
					}
					if (k.canCastle && !k.kingMoved && k.getColor().equals("black")) {
						if (start.getY() > end.getY() && board.piece[0][0] instanceof Rook) {
							if (isKingInCheck("bK", board)) {
								castle = false;
								return false;
							}
							board.piece[end.getX()][end.getY()] = board.piece[start.getX()][start.getY()];
							board.piece[start.getX()][start.getY()] = null;
							board.piece[end.getX()][end.getY() + 1] = board.piece[0][0];
							board.piece[0][0] = null;
							k.kingMoved = true;
							return true;
						} else if (start.getY() < end.getY() && board.piece[0][7] instanceof Rook) {
							if (isKingInCheck("bK", board)) {
								castle = false;
								return false;
							}
							board.piece[end.getX()][end.getY()] = board.piece[start.getX()][start.getY()];
							board.piece[start.getX()][start.getY()] = null;
							board.piece[end.getX()][end.getY() - 1] = board.piece[0][7];
							board.piece[0][7] = null;
							k.kingMoved = true;
							return true;
						} else {
							return false;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * promote the pawn
	 * 
	 * @param start : current position
	 * @param end   : where it wants to go
	 * @param board : current board
	 * @param c     : promotion character
	 * @param color : which piece color is it
	 */
	public void promotePawn(Point start, Point end, Board board, char c, String color) {
		switch (c) {
		case 'Q':
			board.piece[start.getX()][start.getY()] = null;
			board.piece[end.getX()][end.getY()] = new Queen(color.charAt(0) + "Q", color);
			board.render();
			break;
		case 'B':
			board.piece[start.getX()][start.getY()] = null;
			board.piece[end.getX()][end.getY()] = new Bishop(color.charAt(0) + "B", color);
			board.render();
			break;
		case 'N':
			board.piece[start.getX()][start.getY()] = null;
			board.piece[end.getX()][end.getY()] = new Knight(color.charAt(0) + "N", color);
			board.render();
			break;
		case 'R':
			board.piece[start.getX()][start.getY()] = null;
			board.piece[end.getX()][end.getY()] = new Rook(color.charAt(0) + "R", color);
			board.render();
			break;
		default:
			break;
		}
	}

}
