package com.vini.app.piece.classic;

import com.vini.app.board.Board;
import com.vini.app.board.BoardHelper;
import com.vini.app.piece.Piece;
import com.vini.app.piece.PieceHelper;
import com.vini.app.piece.PieceImpl;

public class Queen extends PieceImpl {
	private final int[][] directions = {
		{-1, -1}, {0, -1}, {1, -1},
		{-1, 0}, {1, 0},
		{-1, 1}, {0, 1}, {1, 1}
	};

	@Override
	public Piece updateMoves(Board board) {
		for (int[] direction : this.directions) {
			int[] position = this.position();

			while (true) {
				position[0] = position[0] + direction[0];
				position[1] = position[1] + direction[1];

				if (!BoardHelper.isPositionInsideBoard(position, board)) {
					break;
				};

				Piece target = board.table().get(position()[1]).get(position()[0]);

				if (PieceHelper.isAlly(this, target)) {
					break;
				}

				this.moves().get(position[1]).set(position[0], true);
					
				if (PieceHelper.isEnemy(this, target)) {
					break;
				}
			}
		}

		return this;
	}
}

