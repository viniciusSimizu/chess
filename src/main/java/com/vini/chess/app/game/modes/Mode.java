package com.vini.chess.app.game.modes;

import com.vini.chess.app.game.modes.setup.ISetup;
import com.vini.chess.app.piece.IPieceFactory;

public class Mode {
	private final String description;
	private final ISetup setup;
	private final IPieceFactory factory;

	public Mode(String description, ISetup setup, IPieceFactory factory) {
		this.description = description;
		this.setup = setup;
		this.factory = factory;
	}

	public String description() {
		return this.description;
	}

	public ISetup setup() {
		return this.setup;
	}

	public IPieceFactory factory() {
		return this.factory;
	}
}
