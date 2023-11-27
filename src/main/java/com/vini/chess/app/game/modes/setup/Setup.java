package com.vini.chess.app.game.modes.setup;

import com.vini.chess.app.types.ColorEnum;

public class Setup implements ISetup {
	private final String fen;
	private final ColorEnum turn;

	public Setup(String fen, ColorEnum turn) {
		this.fen = fen;
		this.turn = turn;
	}

	@Override
	public String fen() {
		return this.fen;
	}

	@Override
	public ColorEnum turn() {
		return this.turn;
	}
}

