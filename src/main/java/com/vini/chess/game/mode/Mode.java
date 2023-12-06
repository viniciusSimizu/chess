package com.vini.chess.game.mode;

import com.vini.chess.game.enums.ColorEnum;

public class Mode {
	public final String fen;
	public final ColorEnum turn;

	public Mode(String fen, ColorEnum turn) {
		this.fen = fen;
		this.turn = turn;
	}
}
