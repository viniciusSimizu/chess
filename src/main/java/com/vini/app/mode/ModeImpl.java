package com.vini.app.mode;

import com.vini.app.mode.setup.Setup;
import com.vini.app.piece.PieceFactory;

public class ModeImpl {
	private final String description;
	private final Setup setup;
	private final PieceFactory factory;

	public ModeImpl(String description, Setup setup, PieceFactory factory) {
		this.description = description;
		this.setup = setup;
		this.factory = factory;
	}

	public String description() {
		return this.description;
	}

	public Setup setup() {
		return this.setup;
	}

	public PieceFactory factory() {
		return this.factory;
	}
}
