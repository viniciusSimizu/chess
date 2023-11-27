package com.vini.chess.app.piece;

import java.util.List;

import com.vini.chess.app.piece.commands.PieceMoveCommand;
import com.vini.chess.app.types.ColorEnum;
import com.vini.chess.app.types.PieceEnum;

public class PieceDecorator implements IPiece {
	private IPiece piece;

	public PieceDecorator(IPiece piece) {
		this.piece = piece;
	}

	@Override
	public PieceMoveCommand move(int[] position) {
		return this.piece.move(position);
	}

	@Override
	public boolean canMove(int[] position) {
		return this.piece.canMove(position);
	}

	@Override
	public IPiece structureMoves() {
		this.piece.structureMoves();
		return this;
	}

	@Override
	public IPiece updateMoves() {
		if (!this.piece.hasMovesUpdated()) {
			this.piece.updateMoves();
			this.piece.setHasMovesUpdated(true);
		}
		return this;
	}

	@Override
	public ColorEnum color() {
		return this.piece.color();
	}

	@Override
	public IPiece setColor(ColorEnum color) {
		this.piece.setColor(color);
		return this;
	}

	@Override
	public int[] position() {
		return this.piece.position();
	}

	@Override
	public IPiece setPosition(int[] position) {
		this.piece.setPosition(position);
		return this;
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
	public IPiece setHasMovesUpdated(boolean state) {
		this.piece.setHasMovesUpdated(state);
		return this;
	}

	@Override
	public IPiece setMoves(List<List<Boolean>> moves) {
		this.piece.setMoves(moves);
		return this;
	}

	@Override
	public IPiece resetMoves() {
		this.piece.resetMoves();
		return this;
	}

	@Override
	public PieceEnum fen() {
		return this.piece.fen();
	}

	@Override
	public String toString() {
		return this.piece.toString();
	}
}
