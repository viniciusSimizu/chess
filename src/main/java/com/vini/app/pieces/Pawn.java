package com.vini.app.pieces;

import java.util.List;
import java.util.Objects;

import com.vini.app.types.ColorEnum;

public class Pawn extends Piece implements IPiece {
	final int[] directions = {0, 1};
	final int[][] forkDirections = {{-1, 1}, {1, 1}};
	Boolean hasMoved = false;
	int directionWeight = 1;

	Pawn(ColorEnum color, int[] position) {
		super(color, position);

		if (color == ColorEnum.WHITE) {
			this.directionWeight = -1;
		}
	}

	@Override
	public void move(List<List<Piece>> board, int[] position) {
		board.get(position[1]).set(position[0], this);
		board.get(this.position[1]).set(this.position[0], null);
	}

	@Override
	public Piece updateMoves(final List<List<Piece>> board) {
		int maxDistance = 1;
		if (!this.hasMoved) {
			maxDistance = 2;
		}

		for (int i = 1; i <= maxDistance; i++) {
			int[] targetPosition = {
				this.position[0],
				this.position[1] + i * this.directionWeight
			};

			if (!this.insideBoard(board, targetPosition)) {
				break;
			};

			Piece target = board.get(targetPosition[1]).get(targetPosition[0]);

			if (Objects.nonNull(target)) {
				break;
			}
			this.moves.get(targetPosition[1]).set(targetPosition[0], true);
		}

		for (int[] forkDirection : this.forkDirections) {
			int[] targetPosition = {
				this.position[0] + forkDirection[0],
				this.position[1] + forkDirection[1] * this.directionWeight
			};

			if (!this.insideBoard(board, targetPosition)) {
				continue;
			}

			Piece target = board.get(targetPosition[1]).get(targetPosition[0]);

			if (!Objects.nonNull(target) || this.isFriend(this, target)) {
				continue;	
			}

			this.moves.get(targetPosition[1]).set(targetPosition[0], true);
		}

		return this;
	}
}

