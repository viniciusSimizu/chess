package com.vini.chess.app.translate;

import java.util.EnumMap;
import java.util.Map;

import com.vini.chess.app.types.PieceEnum;

public class PieceEnumIconTranslate {
	public static final Map<PieceEnum, Integer> book = new EnumMap<>(PieceEnum.class);
	static {
		book.put(PieceEnum.PAWN, 0x2659);
		book.put(PieceEnum.ROOK, 0x2656);
		book.put(PieceEnum.KNIGHT, 0x2658);
		book.put(PieceEnum.BISHOP, 0x2657);
		book.put(PieceEnum.QUEEN, 0x2655);
		book.put(PieceEnum.KING, 0x2654);
	}
}
