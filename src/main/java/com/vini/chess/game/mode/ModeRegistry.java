package com.vini.chess.game.mode;

import com.vini.chess.game.enums.ColorEnum;

public class ModeRegistry {
	public static Mode classic;

	static {
		ModeRegistry.classic = ModeRegistry.makeClassic();
	}

	private static Mode makeClassic() {
		String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
		ColorEnum turn = ColorEnum.WHITE;

		Mode mode = new Mode(fen, turn);

		return mode;
	}
}
