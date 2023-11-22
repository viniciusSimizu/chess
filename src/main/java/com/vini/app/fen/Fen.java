package com.vini.app.fen;

import com.vini.app.board.Board;
import com.vini.app.types.ColorEnum;

public interface Fen {
	public Board build(String fenNotation);
	public ColorEnum notationColor(char chr);
}

