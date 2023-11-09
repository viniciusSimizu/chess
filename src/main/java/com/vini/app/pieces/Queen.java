package com.vini.app.pieces;

import java.util.List;
import java.util.Objects;

import com.vini.app.fen.FenPieceEnum;
import com.vini.app.types.ColorEnum;

public class Queen extends Piece implements IPiece {
	Queen(ColorEnum color, int[] position, FenPieceEnum fen) {
		super(color, position, fen);
	}

	private final int[][] directions = {
		{-1, -1}, {0, -1}, {1, -1},
		{-1, 0}, {1, 0},
		{-1, 1}, {0, 1}, {1, 1}
	};

	@Override
	public Piece updateMoves(List<List<Piece>> board) {
		for (int[] direction : this.directions) {
			int[] position = this.position;

			while (true) {
				position[0] = position[0] + direction[0];
				position[1] = position[1] + direction[1];

				if (!this.insideBoard(board, position)) {
					break;
				};

				Piece target = board.get(position[1]).get(position[0]);

				if (Objects.isNull(target)) {
					this.moves.get(position[1]).set(position[0], true);
					continue;
				}

				if (!this.isFriend(this, target)) {
					this.moves.get(position[1]).set(position[0], true);
				}

				break;
			}
		}

		return this;
	}
}

