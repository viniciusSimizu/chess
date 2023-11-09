package com.vini.app.fen;

public enum FenPieceEnum {
	PAWN,
	ROOK,
	KNIGHT,
	BISHOP,
	QUEEN,
	KING;

	public String toString() {
		switch(this) {
			case PAWN:
				return "p";
			case ROOK:
				return "r";
			case KNIGHT:
				return "n";
			case BISHOP:
				return "b";
			case QUEEN:
				return "q";
			case KING:
				return "k";
			default:
				return null;
		}
	}
}

