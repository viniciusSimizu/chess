package com.vini.app.fen;

import com.vini.app.board.Board;
import com.vini.app.board.builder.BoardBuilderImpl;
import com.vini.app.board.iterator.BoardIteratorOverPiece;
import com.vini.app.piece.Piece;
import com.vini.app.piece.PieceFactory;
import com.vini.app.piece.PieceHelper;
import com.vini.app.translate.PieceEnumStringTranslate;
import com.vini.app.types.ColorEnum;
import com.vini.app.types.PieceEnum;

public class FenImpl implements Fen {
	private PieceFactory factory;

	public FenImpl(PieceFactory factory) {
		this.factory = factory;
	}

	public Board build(String fenNotation) {
		BoardBuilderImpl builder = new BoardBuilderImpl(this.factory);

		String command = "";

		for (int i = 0; i < fenNotation.length(); i++) {
			char chr = fenNotation.charAt(i);

			if (Character.isDigit(chr)) {
				command.concat(Character.toString(chr));
				continue;
			}

			if (!command.isEmpty()) {
				for (int j = 0; j < Integer.parseInt(command); j++) {
					builder.buildEmptySquare();
				}
				command = "";
			}

			if (chr == '/') {
				builder.buildRow();
			}

			PieceEnum piece = PieceEnumStringTranslate.stringToPiece(Character.toString(chr));

			if (piece == null) {
				continue;
			}

			builder.buildPiece(piece, this.notationColor(chr));
		}

		if (!command.isEmpty()) {
			for (int j = 0; j < Integer.parseInt(command); j++) {
				builder.buildEmptySquare();
			}
		}

		builder.buildRow();

		Board board = builder.result();

		BoardIteratorOverPiece iterator = new BoardIteratorOverPiece(board);

		while (iterator.hasNext()) {
			Piece piece = iterator.next();
			PieceHelper.structureMoves(piece, board);
		}

		return board;
	}

	@Override
	public ColorEnum notationColor(char chr) {
		if (Character.isLowerCase(chr)) {
			return ColorEnum.BLACK;
		}
		return ColorEnum.WHITE;
	}
}
