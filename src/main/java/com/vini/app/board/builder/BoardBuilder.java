package com.vini.app.board.builder;

import com.vini.app.board.Board;
import com.vini.app.lib.Builder;
import com.vini.app.piece.PieceFactory;
import com.vini.app.types.ColorEnum;
import com.vini.app.types.PieceEnum;

public interface BoardBuilder extends Builder<Board> {
	public BoardBuilder setFactory(PieceFactory factory);
	public BoardBuilder buildPiece(PieceEnum piece, ColorEnum color);
	public BoardBuilder buildRow();
	public BoardBuilder buildEmptySquare();
}
