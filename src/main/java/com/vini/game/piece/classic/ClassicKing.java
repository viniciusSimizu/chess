package com.vini.game.piece.classic;

import com.vini.game.board.Board;
import com.vini.game.piece.IPiece;
import com.vini.game.piece.PieceHelper;
import com.vini.game.piece.abstracts.King;

public class ClassicKing extends King {
	private final int[][] directions = {
		{-1, -1}, {0, -1}, {1, -1},
		{-1, 0}, {1, 0},
		{-1, 1}, {0, 1}, {1, 1}
	};

	public ClassicKing(Board board) {
		super(board);
	}

	@Override
	public IPiece updateMoves() {
		for (int[] direction : this.directions) {
			int[] position = this.position().clone();
			position[0] += direction[0];
			position[1] += direction[1];

			if (!board.isInsideTable(position)) {
				continue;
			};

			IPiece target = board.findPiece(position);

			if (PieceHelper.isAlly(this, target)) {
				continue;
			}

			this.moves().get(position[1]).set(position[0], true);
		}

		return this;
	}
}

