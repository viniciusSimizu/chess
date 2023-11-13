package com.vini.app.pieces;

import java.util.List;

import com.vini.app.types.ColorEnum;

public class PieceDecorator implements IPiece {
	IPiece piece;

	public PieceDecorator(IPiece piece) {
		this.piece = piece;
	}

	@Override
	public IPiece updateMoves(List<List<IPiece>> board) {
		if (!this.piece.movesUpdated()) {
			this.piece.updateMoves(board);
			this.piece.setMovesUpdated(true);
		}
		return this;
	}

	@Override
	public void structMoves(List<List<IPiece>> board) {
		this.piece.structMoves(board);
	}

	@Override
	public void move(List<List<IPiece>> board, int[] position) {
		this.piece.move(board, position);
	}

	@Override
	public boolean canMove(List<List<IPiece>> board, int[] position) {
		return this.piece.canMove(board, position);
	}

	@Override
	public ColorEnum color() {
		return this.piece.color();
	}

	@Override
	public IPiece setColor(ColorEnum color) {
		return this.setColor(color);
	}

	@Override
	public int[] position() {
		return this.piece.position();
	}

	@Override
	public IPiece setPosition(int[] position) {
		return this.setPosition(position);
	}

	@Override
	public List<List<Boolean>> moves() {
		return this.piece.moves();
	}

	@Override
	public String fen() {
		return this.piece.fen();
	}

	@Override
	public IPiece setFen(String fen) {
		return this.piece.setFen(fen);
	}

	@Override
	public boolean movesUpdated() {
		return this.piece.movesUpdated();
	}

	@Override
	public IPiece setMovesUpdated(boolean state) {
		return this.setMovesUpdated(state);
	}
}
