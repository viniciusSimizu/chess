package com.vini.app.pieces;

import java.util.List;

import com.vini.app.fen.FenPieceEnum;
import com.vini.app.types.ColorEnum;

public class Knight extends Piece implements IPiece {
	Knight(ColorEnum color, int[] position, FenPieceEnum fen) {
		super(color, position, fen);
	}

	private final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	private final int forwardStepWight = 2;
	private final int sideStepWeight = 1;

	@Override
	public Piece updateMoves(List<List<Piece>> board) {
		for (int[] direction : this.directions) {
			for (int sideStepDirection : new int[]{-1, 1}) {
				int[] position = {
					this.position[0] + this.forwardStepWight * direction[1],
					this.position[1] + this.forwardStepWight * direction[0]
				};

				position[0] = Math.abs(direction[1]) * sideStepDirection * sideStepWeight;
				position[1] = Math.abs(direction[0]) * sideStepDirection * sideStepWeight;

				if (!this.insideBoard(board, position)) {
					continue;
				}

				Piece target = board.get(position[1]).get(position[0]);

				if (this.isFriend(this, target)) {
					continue;
				}

				this.moves.get(position[1]).set(position[0], true);
			}
		}

		return this;
	}
}

