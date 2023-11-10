package com.vini.app.pieces;

import java.util.List;
import java.util.Objects;

public class Rook extends Piece {
	private final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

	@Override
	public IPiece updateMoves(List<List<IPiece>> board) {
		for (int[] direction : this.directions) {
			int[] position = this.position();

			while (true) {
				position[0] = position[0] + direction[0];
				position[1] = position[1] + direction[1];

				if (!this.insideBoard(board, position)) {
					break;
				};

				IPiece target = board.get(position[1]).get(position[0]);

				if (Objects.isNull(target)) {
					this.moves().get(position[1]).set(position[0], true);
					continue;
				}

				if (!this.isFriend(this, target)) {
					this.moves().get(position[1]).set(position[0], true);
				}

				break;
			}
		}

		return this;
	}
}
