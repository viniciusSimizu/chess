package com.vini.app.board;

import java.util.ArrayList;
import java.util.List;

import com.vini.app.piece.IPiece;

public class Board {
	private List<List<IPiece>> table = new ArrayList<>();

	public Board(List<List<IPiece>> table) {
		this.table = table;
	}

	public IPiece findPiece(int[] position) {
		if (this.isInsideTable(position)) {
			return this.table().get(position[1]).get(position[0]);
		}
		return null;
	}

	public boolean isInsideTable(int[] position) {
		if (position[1] < 0 || position[1] >= this.table().size()) {
			return false;
		}

		if (position[0] >= 0 && position[0] < this.table().get(position[1]).size()) {
			return true;
		}

		return false;
	}

	public List<List<IPiece>> table() {
		return this.table;
	}
}
