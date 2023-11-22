package com.vini.app.piece;

public interface PieceFactory {
	public Piece makePawn();
	public Piece makeRook();
	public Piece makeKnight();
	public Piece makeBishop();
	public Piece makeQueen();
	public Piece makeKing();
}
