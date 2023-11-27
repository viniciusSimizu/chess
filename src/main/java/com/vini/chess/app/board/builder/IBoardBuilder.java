package com.vini.chess.app.board.builder;

import com.vini.chess.app.board.Board;
import com.vini.chess.app.lib.Builder;
import com.vini.chess.app.piece.IPieceFactory;
import com.vini.chess.app.types.ColorEnum;
import com.vini.chess.app.types.PieceEnum;

public interface IBoardBuilder extends Builder<Board> {
	public IBoardBuilder setFactory(IPieceFactory factory);
	public IBoardBuilder buildPiece(PieceEnum piece, ColorEnum color);
	public IBoardBuilder buildRow();
	public IBoardBuilder buildEmptySquare();
}
