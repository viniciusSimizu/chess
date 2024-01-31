package com.vini.game.fen;

import com.vini.game.board.Board;
import com.vini.game.board.BoardBuilder;
import com.vini.game.board.iterator.BoardIteratorOverPiece;
import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.piece.IPiece;
import com.vini.game.translate.PieceEnumStringTranslate;

public class Fen {
	public static final String defaultNotation = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

  private Fen(){};

  public static Board build(String fenNotation) {
    String skips = "";
    BoardBuilder builder = new BoardBuilder();

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

      PieceEnum piece =
          PieceEnumStringTranslate.stringToPiece(Character.toString(chr));

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
