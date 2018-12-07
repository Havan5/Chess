package view;

import model.Piece;

/**
 * @author: Havan Patel
 */

/**
 * 
 * @author HP
 *
 */
public class Board {
	public Piece[][] piece = new Piece[8][8];

	/**
	 * render the board again
	 */
	public void render() {
		System.out.println();
		for (int i = 0; i < piece.length; i++) {
			for (int j = 0; j < piece.length; j++) {
				if (piece[i][j] != null) {
					System.out.print(piece[i][j] + " ");
				} else if ((i + j) % 2 != 0) {
					System.out.print("## ");
				} else {
					System.out.print("   ");
				}
			}
			System.out.println(8 - i);
		}
		System.out.print(" a  b  c  d  e  f  g  h");
		System.out.println("\n");
	}
}
