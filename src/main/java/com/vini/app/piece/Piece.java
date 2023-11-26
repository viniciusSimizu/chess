package com.vini.app.piece;

import java.util.ArrayList;
import java.util.List;

import com.vini.app.board.Board;
import com.vini.app.piece.commands.PieceMoveCommand;
import com.vini.app.types.ColorEnum;

public abstract class Piece implements IPiece {
	protected Board board;

	private ColorEnum color;
	private int[] position = new int[2];
	private List<List<Boolean>> moves = new ArrayList<>();
	private boolean hasMovesUpdated = false;

	protected Piece(Board board) {
		this.board = board;
	}

	public PieceMoveCommand move(int[] position) {
		if (!this.canMove(position)) {
			return null;
		}

		PieceMoveCommand command = new PieceMoveCommand(this.position(), position, this.board);
		command.execute();

		this.resetMoves();

		return command;
	}

	public boolean canMove(int[] position) {
		if (this.hasMovesUpdated() && this.isInsideMoves(position)) {
			return this.moves().get(position[1]).get(position[0]);
		}

		return false;
	}

	public IPiece structureMoves() {
		List<List<Boolean>> moves = new ArrayList<List<Boolean>>();
		List<Boolean> row = new ArrayList<>();

		for (int i = 0; i < this.board.table().size(); i++) {
			for (int j = 0; j < this.board.table().get(i).size(); j++) {
				row.add(false);
			}
			moves.add(row);
			row = new ArrayList<>();
		}

		this.setMoves(moves);
		return this;
	}

	public IPiece resetMoves() {
		if (!this.hasMovesUpdated()) {
			return this;
		}

		for (int i = 0; i < this.moves().size(); i++) {
			for (int j = 0; j < this.moves().get(i).size(); j++) {
				this.moves().get(i).set(j, false);
			}
		};

		this.setHasMovesUpdated(false);
		return this;
	}

	public ColorEnum color() {
		return this.color;
	}
	public IPiece setColor(ColorEnum color) {
		this.color = color;
		return this;
	}

	public int[] position() {
		return this.position;
	}
	public IPiece setPosition(int[] position) {
		this.position = position;
		return this;
	}

	public List<List<Boolean>> moves() {
		return this.moves;
	}
	public IPiece setMoves(List<List<Boolean>> moves) {
		this.moves = moves;
		return this;
	}

	public boolean hasMovesUpdated() {
		return this.hasMovesUpdated;
	}
	public IPiece setHasMovesUpdated(boolean state) {
		this.hasMovesUpdated = state;
		return this;
	}

	protected boolean isInsideMoves(int[] position) {
		if (position[1] < 0 || position[1] >= this.moves().size()) {
			return false;
		}

		if (position[0] >= 0 && position[0] < this.moves().get(position[1]).size()) {
			return true;
		}

		return false;
	}
}
