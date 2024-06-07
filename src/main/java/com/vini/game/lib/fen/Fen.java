package com.vini.game.fen;

import com.vini.game.board.BoardBuilder;
import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IBoard;
import com.vini.game.interfaces.IPiece;
import com.vini.game.translate.PieceEnumStringTranslate;

public class Fen {

    private Fen() {}

    public static final String defaultNotation = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

    public static IBoard build(String fenNotation) throws IllegalArgumentException {

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

            PieceEnum pieceTag = PieceEnumStringTranslate.stringToPiece(Character.toString(chr));

            if (pieceTag == null) {
                throw new IllegalArgumentException("Invalid data: %s".formatted(chr));
            }

            builder.buildPiece(pieceTag, Fen.notationColor(chr));
        }
        builder.buildRow();

        IBoard board = builder.getResult();

        var iterator = board.iteratorOverPiece();
        while (iterator.hasNext()) {
            IPiece piece = iterator.next();
            piece.setBoard(board);
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
