package com.vini.app.setups;

import java.util.Map;

import com.vini.app.pieces.IPiece;
import com.vini.app.types.ColorEnum;

public class SetupData {
	public String fen;
	public ColorEnum turn;
	public Map<String, Class<? extends IPiece>> pieceMap;
	
	public SetupData setFen(String fen) {
		this.fen = fen;
		return this;
	}

	public SetupData setTurn(ColorEnum turn) {
		this.turn = turn;
		return this;
	}

	public SetupData setPieceMap(Map<String, Class<? extends IPiece>> map) {
		this.pieceMap = map;
		return this;
	}
}

