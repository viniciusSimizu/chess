package com.vini.app.board;

import java.util.ArrayList;
import java.util.List;

import com.vini.app.piece.Piece;

public class Board {
	private List<List<Piece>> table = new ArrayList<>();

	public Piece piece(int[] position) {
		if (BoardHelper.isPositionInsideBoard(position, this)) {
			return this.table().get(position[1]).get(position[0]);
		}
		return null;
	}

	public List<List<Piece>> table() {
		return this.table;
	}

	public Board setTable(List<List<Piece>> board) {
		this.table = board;
		return this;
	}
}
