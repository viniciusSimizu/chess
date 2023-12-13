package com.vini.game.piece.classic;

import com.vini.game.board.Board;
import com.vini.game.piece.IPiece;
import com.vini.game.piece.PieceHelper;
import com.vini.game.piece.abstracts.Bishop;

public class ClassicBishop extends Bishop {
	private final int[][] directions = {{1, -1}, {1, 1}, {-1, 1}, {-1, -1}};

	public ClassicBishop(Board board) {
		super(board);
	}

	@Override
	public IPiece updateMoves() {
		for (int[] direction : this.directions) {
			int[] position = this.position().clone();

			while (true) {
				position[0] += direction[0];
				position[1] += direction[1];

				if (!this.board.isInsideTable(position)) {
					break;
				};

				IPiece target = board.findPiece(position);

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
