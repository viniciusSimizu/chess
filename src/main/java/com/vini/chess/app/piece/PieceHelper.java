package com.vini.chess.app.piece;

public class PieceHelper {
	public static boolean isAlly(IPiece source, IPiece target) {
		if (source == null || target == null) {
			return false;
		}

		return source.color() == target.color();
	}

	public static boolean isEnemy(IPiece source, IPiece target) {
		if (source == null || target == null) {
			return false;
		}

		return source.color() != target.color();
	}
}
