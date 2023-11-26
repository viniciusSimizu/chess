package com.vini.app.board.builder;

import com.vini.app.board.Board;
import com.vini.app.lib.Builder;
import com.vini.app.piece.IPieceFactory;
import com.vini.app.types.ColorEnum;
import com.vini.app.types.PieceEnum;

public interface IBoardBuilder extends Builder<Board> {
	public IBoardBuilder setFactory(IPieceFactory factory);
	public IBoardBuilder buildPiece(PieceEnum piece, ColorEnum color);
	public IBoardBuilder buildRow();
	public IBoardBuilder buildEmptySquare();
}
