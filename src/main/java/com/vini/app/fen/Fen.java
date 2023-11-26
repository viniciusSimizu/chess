package com.vini.app.fen;

import com.vini.app.board.Board;
import com.vini.app.board.builder.BoardBuilder;
import com.vini.app.board.iterator.BoardIteratorOverPiece;
import com.vini.app.piece.IPiece;
import com.vini.app.piece.IPieceFactory;
import com.vini.app.translate.PieceEnumStringTranslate;
import com.vini.app.types.ColorEnum;
import com.vini.app.types.PieceEnum;

public class Fen {
	public static Board build(IPieceFactory factory, String fenNotation) {
		String skips = "";
		BoardBuilder builder = new BoardBuilder(factory);

		for (int i = 0; i < fenNotation.length(); i++) {
			char chr = fenNotation.charAt(i);

			if (Character.isDigit(chr)) {
				skips = skips.concat(Character.toString(chr));
				continue;
			}

			if (!skips.isEmpty()) {
				for (int j = 0; j < Integer.parseInt(skips); j++) {
					builder.buildEmptySquare();
				}
				skips = "";
			}

			if (chr == '/') {
				builder.buildRow();
				continue;
			}

			PieceEnum piece = PieceEnumStringTranslate.stringToPiece(Character.toString(chr));

			if (piece == null) {
				continue;
			}

			builder.buildPiece(piece, Fen.notationColor(chr));
		}

		if (!skips.isEmpty()) {
			for (int j = 0; j < Integer.parseInt(skips); j++) {
				builder.buildEmptySquare();
			}
		}
		builder.buildRow();

		Board board = builder.result();

		BoardIteratorOverPiece iterator = new BoardIteratorOverPiece(board);
		while (iterator.hasNext()) {
			IPiece piece = iterator.next();
			piece.structureMoves();
		}

		return board;
	}

	private static ColorEnum notationColor(char chr) {
		if (Character.isLowerCase(chr)) {
			return ColorEnum.BLACK;
		}
		return ColorEnum.WHITE;
	}
}
