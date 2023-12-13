package com.vini.game.piece.pieces;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.piece.IPiece;
import com.vini.game.piece.Piece;
import com.vini.game.piece.PieceHelper;

public class Pawn extends Piece {
	private final int[][] forkDirections = {{-1, 1}, {1, 1}};
	private final int[][] enPassantDirections = {{-1, 0}, {1, 0}};

	public boolean hasMovedTwo = false;

	protected boolean isFirstMove = true;
	protected int directionWeight = 1;

	public Pawn(Board board) {
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

			if (!this.board.isInsideTable(position)) {
				continue;
			}

			IPiece target = board.findPiece(position);

			if (PieceHelper.isEnemy(this, target)) {
				this.moves().get(position[1]).set(position[0], true);
			}
		}

		for (int[] enPassantDirection : this.enPassantDirections) {
			int[] position = this.position().clone();
			position[0] += enPassantDirection[0];

			if (!this.board.isInsideTable(position)) {
				continue;
			}

			IPiece target = board.findPiece(position);

			if (!PieceHelper.isEnemy(this, target) || !(target instanceof Pawn)) {
				continue;
			}

			Pawn pawnTarget = (Pawn) target;

			if (pawnTarget.hasMovedTwo) {
				int[] enPassantPosition = position.clone();
				enPassantPosition[1] += this.directionWeight;

				if (!this.board.isInsideTable(enPassantPosition)) {
					continue;
				}


				this.moves().get(enPassantPosition[1]).set(enPassantPosition[0], true);
			}
		}

		return this;
	}

	@Override
	public void move(int[] position) {
		int heightDiff = Math.abs(position[1] - this.position()[1]);
		if (this.isFirstMove && heightDiff == 2) {
			this.hasMovedTwo = true;
		}

		this.isFirstMove = false;
	}

	@Override
	public IPiece setColor(ColorEnum color) {
		super.setColor(color);

		if (color == ColorEnum.WHITE) {
			this.directionWeight = -1;
		}

		return this;
	}

	@Override
	public PieceEnum fen() {
		return PieceEnum.PAWN;
	}
}

