package com.vini.app.piece;

import java.util.ArrayList;
import java.util.List;

import com.vini.app.types.ColorEnum;

public abstract class PieceImpl implements Piece {
	private ColorEnum color;
	private int[] position = new int[2];
	private List<List<Boolean>> moves = new ArrayList<>();
	private boolean hasMovesUpdated = false;

	public ColorEnum color() {
		return this.color;
	}
	public Piece setColor(ColorEnum color) {
		this.color = color;
		return this;
	}

	public int[] position() {
		return this.position;
	}
	public Piece setPosition(int[] position) {
		this.position = position;
		return this;
	}

	public List<List<Boolean>> moves() {
		return this.moves;
	}
	public Piece setMoves(List<List<Boolean>> moves) {
		this.moves = moves;
		return this;
	}

	public boolean hasMovesUpdated() {
		return this.hasMovesUpdated;
	}
	public Piece setHasMovesUpdated(boolean state) {
		this.hasMovesUpdated = state;
		return this;
	}
}
