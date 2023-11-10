package com.vini.app.translate;

import java.util.HashMap;
import java.util.Map;

import com.vini.app.fen.FenPieceEnum;

public class PieceDisplayTranslate {
	public static final Map<String, Integer> book = new HashMap<String, Integer>();
	static {
		book.put(FenPieceEnum.PAWN.toString(), 0x2659);
		book.put(FenPieceEnum.ROOK.toString(), 0x2656);
		book.put(FenPieceEnum.KNIGHT.toString(), 0x2658);
		book.put(FenPieceEnum.BISHOP.toString(), 0x2657);
		book.put(FenPieceEnum.QUEEN.toString(), 0x2655);
		book.put(FenPieceEnum.KING.toString(), 0x2654);
	}
}
