package model;

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
public class Player {
	public String color;
	public Board b;
	public boolean won = false;
	public boolean draw = false;

	/**
	 * initialize the player class
	 * 
	 * @param color : players color
	 * @param b     : get the chess board
	 */
	public Player(String color, Board b) {
		this.color = color;
		this.b = b;
	}

	/**
	 * set up the pieces on the board for the black/white players
	 */
	public void setUpPieces() {
		if (color.equalsIgnoreCase("Black")) {
			b.piece[0][0] = new Rook("bR", color);
			b.piece[0][1] = new Knight("bN", color);
			b.piece[0][2] = new Bishop("bB", color);
			b.piece[0][3] = new Queen("bQ", color);
			b.piece[0][4] = new King("bK", color);
			b.piece[0][5] = new Bishop("bB", color);
			b.piece[0][6] = new Knight("bN", color);
			b.piece[0][7] = new Rook("bR", color);

			for (int i = 0; i < b.piece[1].length; i++) {
				b.piece[1][i] = new Pawn("bp", color);
			}
		} else {
			b.piece[7][0] = new Rook("wR", color);
			b.piece[7][1] = new Knight("wN", color);
			b.piece[7][2] = new Bishop("wB", color);
			b.piece[7][3] = new Queen("wQ", color);
			b.piece[7][4] = new King("wK", color);
			b.piece[7][5] = new Bishop("wB", color);
			b.piece[7][6] = new Knight("wN", color);
			b.piece[7][7] = new Rook("wR", color);

			for (int i = 0; i < b.piece[6].length; i++) {
				b.piece[6][i] = new Pawn("wp", color);
			}
		}
	}

	/**
	 * 
	 * @return player name
	 */
	public String getName() {
		return this.color;
	}
}
