package com.vini.app.pieces;

import java.util.List;
import java.util.Objects;

import com.vini.app.types.ColorEnum;

public class Pawn extends Piece {
	private final int[][] forkDirections = {{-1, 1}, {1, 1}};
	private Boolean hasMoved = false;
	private int directionWeight = 1;

	@Override
	public void move(List<List<IPiece>> board, int[] position) {
		super.move(board, position);

		if (!this.hasMoved) {
			this.hasMoved = true;
		}
	}

	@Override
	public IPiece updateMoves(final List<List<IPiece>> board) {
		int maxDistance = 1;

		if (!this.hasMoved) {
			maxDistance = 2;
		}

		for (int i = 1; i <= maxDistance; i++) {
			int[] targetPosition = {
				this.position()[0],
				this.position()[1] + i * this.directionWeight
			};

			if (!this.insideBoard(board, targetPosition)) {
				break;
			};

			IPiece target = board.get(targetPosition[1]).get(targetPosition[0]);

			if (Objects.nonNull(target)) {
				break;
			}
			this.moves().get(targetPosition[1]).set(targetPosition[0], true);
		}

		for (int[] forkDirection : this.forkDirections) {
			int[] targetPosition = {
				this.position()[0] + forkDirection[0],
				this.position()[1] + forkDirection[1] * this.directionWeight
			};

			if (!this.insideBoard(board, targetPosition)) {
				continue;
			}

			IPiece target = board.get(targetPosition[1]).get(targetPosition[0]);

			if (Objects.isNull(target) || this.isFriend(this, target)) {
				continue;	
			}

			this.moves().get(targetPosition[1]).set(targetPosition[0], true);
		}

		return this;
	}

	public IPiece setColor(ColorEnum color) {
		super.setColor(color);
		if (color == ColorEnum.WHITE) {
			this.directionWeight = -1;
		}
		return this;
	}
}

