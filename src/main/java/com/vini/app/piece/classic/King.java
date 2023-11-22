package com.vini.app.piece.classic;

import com.vini.app.board.Board;
import com.vini.app.board.BoardHelper;
import com.vini.app.piece.Piece;
import com.vini.app.piece.PieceHelper;
import com.vini.app.piece.PieceImpl;

public class King extends PieceImpl {
	private final int[][] directions = {
		{-1, -1}, {0, -1}, {1, -1},
		{-1, 0}, {1, 0},
		{-1, 1}, {0, 1}, {1, 1}
	};

	@Override
	public Piece updateMoves(Board board) {
		for (int[] direction : this.directions) {
			int[] position = {
				this.position()[0] + direction[0],
				this.position()[1] + direction[1]
			};

			if (!BoardHelper.isPositionInsideBoard(position, board)) {
				continue;
			};

			Piece target = board.table().get(position()[1]).get(position()[0]);

			if (PieceHelper.isAlly(this, target)) {
				continue;
			}

			this.moves().get(position[1]).set(position[0], true);
		}

		return this;
	}
}

