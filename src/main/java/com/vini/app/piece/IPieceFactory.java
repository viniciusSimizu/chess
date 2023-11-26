package com.vini.app.piece;

import com.vini.app.board.Board;

public interface IPieceFactory {
	public IPiece makePawn(Board board);
	public IPiece makeRook(Board board);
	public IPiece makeKnight(Board board);
	public IPiece makeBishop(Board board);
	public IPiece makeQueen(Board board);
	public IPiece makeKing(Board board);
}
