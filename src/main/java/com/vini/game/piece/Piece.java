package com.vini.game.piece;

import java.util.ArrayList;
import java.util.List;

import com.vini.game.Game;
import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.piece.commands.PieceMoveCommand;

public abstract class Piece implements IPiece {
	protected Game game;
	protected Board board;

	private ColorEnum color;
	private int[] position = new int[2];
	private List<List<Boolean>> moves = new ArrayList<>();

	protected Piece(Board board) {
		this.board = board;
	}

	public IPiece setGame(Game game) {
		this.game = game;
		return this;
	}

	@Override
	public PieceMoveCommand move(int[] position) {
		if (!this.canMove(position)) {
			return null;
		}

		PieceMoveCommand command = new PieceMoveCommand(this.position(), position, this.board);
		command.execute();

		this.resetMoves();

		return command;
	}

	@Override
	public boolean canMove(int[] position) {
		if (this.isInsideMoves(position)) {
			return this.moves().get(position[1]).get(position[0]);
		}

		return false;
	}

	@Override
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

	@Override
	public void resetMoves() {
		for (int i = 0; i < this.moves().size(); i++) {
			for (int j = 0; j < this.moves().get(i).size(); j++) {
				this.moves().get(i).set(j, false);
			}
		};
	}

	@Override
	public IPiece updateMoves() {
		if (this.moves.size() == 0) {
			this.structureMoves();
		}

		this.resetMoves();
		return this;
	}

	@Override
	public ColorEnum color() {
		return this.color;
	}
	@Override
	public IPiece setColor(ColorEnum color) {
		this.color = color;
		return this;
	}

	@Override
	public int[] position() {
		return this.position;
	}
	@Override
	public IPiece setPosition(int[] position) {
		this.position = position;
		return this;
	}

	@Override
	public List<List<Boolean>> moves() {
		return this.moves;
	}
	@Override
	public IPiece setMoves(List<List<Boolean>> moves) {
		this.moves = moves;
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
