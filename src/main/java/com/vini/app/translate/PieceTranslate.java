package com.vini.app.translate;

import java.util.HashMap;
import java.util.Map;

import com.vini.app.fen.FenPieceEnum;

public class PieceTranslate {
	public static Map<FenPieceEnum, String> book;
	{
		book = new HashMap<FenPieceEnum, String>();
		book.put(FenPieceEnum.PAWN, "Pawn");
		book.put(FenPieceEnum.ROOK, "Rook");
		book.put(FenPieceEnum.KNIGHT, "Knight");
		book.put(FenPieceEnum.BISHOP, "Bishop");
		book.put(FenPieceEnum.QUEEN, "Queen");
		book.put(FenPieceEnum.KING, "King");
	}
}
