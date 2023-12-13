package com.vini.game.piece.classic;

import com.vini.game.board.Board;
import com.vini.game.piece.IPiece;

public class ClassicPieceFactory {
	private static ClassicPieceFactory instance;

	private ClassicPieceFactory() {}

	public static ClassicPieceFactory getInstance() {
		if (ClassicPieceFactory.instance == null) {
			ClassicPieceFactory.instance = new ClassicPieceFactory();
		}
		return ClassicPieceFactory.instance;
	}

	public IPiece makePawn(Board board) {
		return new ClassicPawn(board);
	}

	public IPiece makeRook(Board board) {
		return new ClassicRook(board);
	}

	public IPiece makeKnight(Board board) {
		return new ClassicKnight(board);
	}

	public IPiece makeBishop(Board board) {
		return new ClassicBishop(board);
	}

	public IPiece makeQueen(Board board) {
		return new ClassicQueen(board);
	}

	public IPiece makeKing(Board board) {
		return new ClassicKing(board);
	}
}
