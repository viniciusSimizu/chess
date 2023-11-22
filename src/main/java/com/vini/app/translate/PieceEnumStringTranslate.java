package com.vini.app.translate;

import com.vini.app.types.PieceEnum;

public class PieceEnumStringTranslate {
	public static PieceEnum stringToPiece(String str) {
		switch (str.toLowerCase()) {
			case "p":
				return PieceEnum.PAWN;
			case "r":
				return PieceEnum.ROOK;
			case "n":
				return PieceEnum.KNIGHT;
			case "b":
				return PieceEnum.BISHOP;
			case "q":
				return PieceEnum.QUEEN;
			case "k":
				return PieceEnum.KING;
			default:
				return null;
		}
	}
}
