package com.vini.app.piece.classic;

import com.vini.app.board.Board;
import com.vini.app.board.BoardHelper;
import com.vini.app.piece.Piece;
import com.vini.app.piece.PieceHelper;
import com.vini.app.piece.PieceImpl;

public class Knight extends PieceImpl {
	private final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	private final int forwardStepWeight = 2;
	private final int sideStepWeight = 1;

	@Override
	public Piece updateMoves(Board board) {
		for (int[] direction : this.directions) {
			for (int sideStepDirection : new int[]{-1, 1}) {
				int[] position = {
					this.position()[0] + this.forwardStepWeight * direction[0],
					this.position()[1] + this.forwardStepWeight * direction[1]
				};

				position[0] += Math.abs(direction[1]) * sideStepDirection * sideStepWeight;
				position[1] += Math.abs(direction[0]) * sideStepDirection * sideStepWeight;

				if (!BoardHelper.isPositionInsideBoard(position, board)) {
					continue;
				}

				Piece target = board.table().get(position[1]).get(position[0]);

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

