package com.vini.app.mode;

import com.vini.app.mode.setup.Setup;
import com.vini.app.mode.setup.SetupImpl;
import com.vini.app.piece.classic.ClassicPieceFactory;
import com.vini.app.types.ColorEnum;

public class ModeFactory {
	private static ModeFactory instance;

	private ModeFactory() {};

	public ModeImpl makeClassic() {
		String description = "The classic experience of chess";

		String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
		ColorEnum turn = ColorEnum.WHITE;

		Setup setup = new SetupImpl(fen, turn);
		ModeImpl mode = new ModeImpl(description, setup, ClassicPieceFactory.getInstance());

		return mode;
	}

	public static ModeFactory instance() {
		if (ModeFactory.instance == null) {
			ModeFactory.instance = new ModeFactory();
		}
		return ModeFactory.instance;
	}
}
