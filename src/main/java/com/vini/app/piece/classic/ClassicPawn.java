package com.vini.app.piece.classic;

import com.vini.app.board.Board;
import com.vini.app.piece.IPiece;
import com.vini.app.piece.PieceHelper;
import com.vini.app.piece.abstracts.Pawn;

public class ClassicPawn extends Pawn {
	private final int[][] forkDirections = {{-1, 1}, {1, 1}};

	protected ClassicPawn(Board board) {
		super(board);
	}

	@Override
	public IPiece updateMoves() {
		int maxDistance = 1;

		if (this.isFirstMove) {
			maxDistance = 2;
		}

		for (int i = 1; i <= maxDistance; i++) {
			int[] position = this.position().clone();
			position[1] += i * this.directionWeight;

			if (!board.isInsideTable(position)) {
				break;
			};

			IPiece target = board.findPiece(position);

			if (target != null) {
				break;
			}

			this.moves().get(position[1]).set(position[0], true);
		}

		for (int[] forkDirection : this.forkDirections) {
			int[] position = this.position().clone();
			position[0] += forkDirection[0];
			position[1] += forkDirection[1] * this.directionWeight;

			if (!board.isInsideTable(position)) {
				continue;
			}

			IPiece target = board.findPiece(position);

			if (PieceHelper.isEnemy(this, target)) {
				this.moves().get(position[1]).set(position[0], true);
			}
		}

		return this;
	}
}

