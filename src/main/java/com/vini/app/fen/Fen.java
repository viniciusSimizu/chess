package com.vini.app.fen;

import java.util.Map;

import com.vini.app.pieces.Pawn;
import com.vini.app.pieces.Piece;

public class Fen {
	Map<Character, Class<Piece>> pieceMap = Map.of(
		'P', Pawn
	);

	public static Piece[][] build(String fen) {
		
	}
}

