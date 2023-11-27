package com.vini.chess.app.game.modes;

import java.util.ArrayList;
import java.util.List;

import com.vini.chess.app.game.modes.setup.ISetup;
import com.vini.chess.app.game.modes.setup.Setup;
import com.vini.chess.app.piece.classic.ClassicPieceFactory;
import com.vini.chess.app.types.ColorEnum;

public class ModeRegistry {
	private static ModeRegistry instance;

	public List<Mode> registry = new ArrayList<>();

	private ModeRegistry() {
		this.registry.add(this.makeClassic());
	}

	private Mode makeClassic() {
		String description = "The classic experience of chess";

		String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
		ColorEnum turn = ColorEnum.WHITE;

		ISetup setup = new Setup(fen, turn);
		Mode mode = new Mode(description, setup, ClassicPieceFactory.getInstance());

		return mode;
	}

	public static ModeRegistry instance() {
		if (ModeRegistry.instance == null) {
			ModeRegistry.instance = new ModeRegistry();
		}
		return ModeRegistry.instance;
	}
}
