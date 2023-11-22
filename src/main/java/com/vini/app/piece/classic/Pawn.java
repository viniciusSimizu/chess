package com.vini.app.piece.classic;

import com.vini.app.board.Board;
import com.vini.app.board.BoardHelper;
import com.vini.app.piece.Piece;
import com.vini.app.piece.PieceHelper;
import com.vini.app.piece.PieceImpl;
import com.vini.app.types.ColorEnum;

public class Pawn extends PieceImpl {
	private final int[][] forkDirections = {{-1, 1}, {1, 1}};
	private Boolean hasMovesUpdated = false;
	private int directionWeight = 1;

	@Override
	public Piece updateMoves(Board board) {
		int maxDistance = 1;

		if (!this.hasMovesUpdated) {
			maxDistance = 2;
		}

		for (int i = 1; i <= maxDistance; i++) {
			int[] targetPosition = {
				this.position()[0],
				this.position()[1] + i * this.directionWeight
			};

			if (!BoardHelper.isPositionInsideBoard(targetPosition, board)) {
				break;
			};

			Piece target = board.table().get(targetPosition[1]).get(targetPosition[0]);

			if (target != null) {
				break;
			}

			this.moves().get(targetPosition[1]).set(targetPosition[0], true);
		}

		for (int[] forkDirection : this.forkDirections) {
			int[] targetPosition = {
				this.position()[0] + forkDirection[0],
				this.position()[1] + forkDirection[1] * this.directionWeight
			};

			if (!BoardHelper.isPositionInsideBoard(targetPosition, board)) {
				continue;
			}

			Piece target = board.table().get(targetPosition[1]).get(targetPosition[0]);

			if (PieceHelper.isEnemy(this, target)) {
				continue;	
			}

			this.moves().get(targetPosition[1]).set(targetPosition[0], true);
		}

		return this;
	}

	public Piece setColor(ColorEnum color) {
		super.setColor(color);
		if (color == ColorEnum.WHITE) {
			this.directionWeight = -1;
		}
		return this;
	}
}

