package com.vini.app.mode.setup;

import com.vini.app.types.ColorEnum;

public class SetupImpl implements Setup {
	private String fen;
	private ColorEnum turn;

	public SetupImpl(String fen, ColorEnum turn) {
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

