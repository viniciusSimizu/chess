package com.vini.app.board;

import java.util.List;

import com.vini.app.piece.Piece;

public class BoardHelper {
	public static boolean isPositionInsideBoard(int[] position, Board board) {
		List<List<Piece>> table = board.table();

		if (position[1] >= table.size()) {
			return false;
		}

		List<Piece> row = table.get(position[1]);

		if (position[0] < row.size()) {
			return true;
		}

		return false;
	};
}
