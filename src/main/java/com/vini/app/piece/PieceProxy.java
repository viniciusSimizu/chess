package com.vini.app.piece;

import java.util.List;

import com.vini.app.board.Board;
import com.vini.app.types.ColorEnum;

public class PieceProxy implements Piece {
	private Piece piece;

	public PieceProxy(Piece piece) {
		this.piece = piece;
	}

	@Override
	public Piece updateMoves(Board board) {
		if (!this.piece.hasMovesUpdated()) {
			this.piece.updateMoves(board);
			this.piece.setHasMovesUpdated(true);
		}
		return this;
	}


	@Override
	public ColorEnum color() {
		return this.piece.color();
	}

	@Override
	public Piece setColor(ColorEnum color) {
		return this.piece.setColor(color);
	}

	@Override
	public int[] position() {
		return this.piece.position();
	}

	@Override
	public Piece setPosition(int[] position) {
		return this.piece.setPosition(position);
	}

	@Override
	public List<List<Boolean>> moves() {
		return this.piece.moves();
	}

	@Override
	public boolean hasMovesUpdated() {
		return this.piece.hasMovesUpdated();
	}

	@Override
	public Piece setHasMovesUpdated(boolean state) {
		return this.piece.setHasMovesUpdated(state);
	}

	@Override
	public Piece setMoves(List<List<Boolean>> moves) {
		return this.piece.setMoves(moves);
	}
}
