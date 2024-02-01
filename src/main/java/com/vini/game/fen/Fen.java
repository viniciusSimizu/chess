package com.vini.game.fen;

import com.vini.game.board.Board;
import com.vini.game.board.BoardBuilder;
import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.piece.IPiece;
import com.vini.game.translate.PieceEnumStringTranslate;

import java.util.Iterator;

public class Fen {

    private Fen() {}
    ;

    public static final String defaultNotation = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

    public static Board build(String fenNotation) throws IllegalArgumentException {

        BoardBuilder builder = new BoardBuilder();

        for (char chr : fenNotation.toCharArray()) {
            if (Character.isDigit(chr)) {
                for (int j = 0; j < Character.getNumericValue(chr); j++) {
                    builder.buildEmptySquare();
                }
                continue;
            }

            if (chr == '/') {
                builder.buildRow();
                continue;
            }

            PieceEnum piece = PieceEnumStringTranslate.stringToPiece(Character.toString(chr));

            if (piece == null) {
                throw new IllegalArgumentException("Invalid data: %s".formatted(chr));
            }

            builder.buildPiece(piece, Fen.notationColor(chr));
        }
        builder.buildRow();

        Board board = builder.getResult();

        Iterator<IPiece> iterator = board.iteratorOverPiece();
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
