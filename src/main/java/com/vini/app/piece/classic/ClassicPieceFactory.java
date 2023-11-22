package com.vini.app.piece.classic;

import com.vini.app.piece.Piece;
import com.vini.app.piece.PieceFactory;

public class ClassicPieceFactory implements PieceFactory {
	private static ClassicPieceFactory instance;

	private ClassicPieceFactory() {}

	public static ClassicPieceFactory getInstance() {
		if (ClassicPieceFactory.instance == null) {
			ClassicPieceFactory.instance = new ClassicPieceFactory();
		}
		return ClassicPieceFactory.instance;
	}

	@Override
	public Piece makePawn() {
		return new Pawn();
	}
	@Override
	public Piece makeRook() {
		return new Rook();
	}
	@Override
	public Piece makeKnight() {
		return new Knight();
	}
	@Override
	public Piece makeBishop() {
		return new Bishop();
	}
	@Override
	public Piece makeQueen() {
		return new Queen();
	}
	@Override
	public Piece makeKing() {
		return new King();
	}
}
