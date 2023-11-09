package com.vini.app.menu.setups;

import java.util.Map;

import com.vini.app.pieces.Piece;
import com.vini.app.types.ColorEnum;

public class SetupData {
	public String fen;
	public ColorEnum turn;
	public Map<String, Class<? extends Piece>> pieceMap;
	
	public SetupData setFen(String fen) {
		this.fen = fen;
		return this;
	}

	public SetupData setTurn(ColorEnum turn) {
		this.turn = turn;
		return this;
	}

	public SetupData setPieceMap(Map<String, Class<? extends Piece>> map) {
		this.pieceMap = map;
		return this;
	}
}

