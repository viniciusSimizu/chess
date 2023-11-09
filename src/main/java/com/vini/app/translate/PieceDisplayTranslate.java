package com.vini.app.translate;

import java.util.HashMap;
import java.util.Map;

import com.vini.app.fen.FenPieceEnum;

public class PieceDisplayTranslate {
	public static Map<FenPieceEnum, String> book;
	{
		book = new HashMap<FenPieceEnum, String>();
		book.put(FenPieceEnum.PAWN, "\u2656");
		book.put(FenPieceEnum.ROOK, "\u2656");
		book.put(FenPieceEnum.KNIGHT, "\u2658");
		book.put(FenPieceEnum.BISHOP, "\u2657");
		book.put(FenPieceEnum.QUEEN, "\u2655");
		book.put(FenPieceEnum.KING, "\u2654");
	}
}
