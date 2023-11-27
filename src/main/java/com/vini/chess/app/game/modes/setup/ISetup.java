package com.vini.chess.app.game.modes.setup;

import com.vini.chess.app.types.ColorEnum;

public interface ISetup {
	public ColorEnum turn();
	public String fen();
}

