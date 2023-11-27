package com.vini.chess.app.piece.classic;

import com.vini.chess.app.board.Board;
import com.vini.chess.app.piece.IPiece;
import com.vini.chess.app.piece.IPieceFactory;
import com.vini.chess.app.piece.PieceDecorator;

public class ClassicPieceFactory implements IPieceFactory {
	private static ClassicPieceFactory instance;

	private ClassicPieceFactory() {}

	public static ClassicPieceFactory getInstance() {
		if (ClassicPieceFactory.instance == null) {
			ClassicPieceFactory.instance = new ClassicPieceFactory();
		}
		return ClassicPieceFactory.instance;
	}

	@Override
	public IPiece makePawn(Board board) {
		return new PieceDecorator(new ClassicPawn(board));
	}
	@Override
	public IPiece makeRook(Board board) {
		return new PieceDecorator(new ClassicRook(board));
	}
	@Override
	public IPiece makeKnight(Board board) {
		return new PieceDecorator(new ClassicKnight(board));
	}
	@Override
	public IPiece makeBishop(Board board) {
		return new PieceDecorator(new ClassicBishop(board));
	}
	@Override
	public IPiece makeQueen(Board board) {
		return new PieceDecorator(new ClassicQueen(board));
	}
	@Override
	public IPiece makeKing(Board board) {
		return new PieceDecorator(new ClassicKing(board));
	}
}
