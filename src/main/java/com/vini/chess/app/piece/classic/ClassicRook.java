package com.vini.chess.app.piece.classic;

import com.vini.chess.app.board.Board;
import com.vini.chess.app.piece.IPiece;
import com.vini.chess.app.piece.PieceHelper;
import com.vini.chess.app.piece.abstracts.Rook;

public class ClassicRook extends Rook {
	private final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

	protected ClassicRook(Board board) {
		super(board);
	}

	@Override
	public IPiece updateMoves() {
		for (int[] direction : this.directions) {
			int[] position = this.position().clone();

			while (true) {
				position[0] += direction[0];
				position[1] += direction[1];

				if (!board.isInsideTable(position)) {
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
