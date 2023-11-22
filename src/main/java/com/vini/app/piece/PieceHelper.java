package com.vini.app.piece;

import java.util.ArrayList;
import java.util.List;

import com.vini.app.board.Board;

public class PieceHelper {
	public static void resetMoves(Piece piece) {
		for (int i = 0; i < piece.moves().size(); i++) {
			for (int j = 0; j < piece.moves().get(i).size(); j++) {
				piece.moves().get(i).set(j, false);
			}
		};
		piece.setHasMovesUpdated(false);
	}

	public static void structureMoves(Piece piece, Board board) {
		List<List<Boolean>> moves = new ArrayList<List<Boolean>>();
		List<Boolean> row = new ArrayList<>();

		for (int i = 0; i < board.table().size(); i++) {
			for (int j = 0; j < board.table().get(i).size(); j++) {
				row.add(false);
			}
			moves.add(row);
		}

		piece.setMoves(moves);
	}

	public static boolean isAlly(Piece source, Piece target) {
		if (target == null) {
			return false;
		}

		return source.color().equals(target.color());
	}

	public static boolean isEnemy(Piece source, Piece target) {
		if (target == null) {
			return false;
		}

		return !source.color().equals(target.color());
	}
}
