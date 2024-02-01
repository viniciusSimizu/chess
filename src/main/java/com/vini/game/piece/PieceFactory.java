package com.vini.game.piece;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.piece.pieces.Bishop;
import com.vini.game.piece.pieces.King;
import com.vini.game.piece.pieces.Knight;
import com.vini.game.piece.pieces.Pawn;
import com.vini.game.piece.pieces.Queen;
import com.vini.game.piece.pieces.Rook;

public class PieceFactory {
    private static PieceFactory instance;

    private PieceFactory() {}

    public static PieceFactory getInstance() {
        if (PieceFactory.instance == null) {
            PieceFactory.instance = new PieceFactory();
        }
        return PieceFactory.instance;
    }

    public IPiece makePawn(Board board, ColorEnum color) {
        return new Pawn(board, color);
    }

    public IPiece makeRook(Board board, ColorEnum color) {
        return new Rook(board, color);
    }

    public IPiece makeKnight(Board board, ColorEnum color) {
        return new Knight(board, color);
    }

    public IPiece makeBishop(Board board, ColorEnum color) {
        return new Bishop(board, color);
    }

    public IPiece makeQueen(Board board, ColorEnum color) {
        return new Queen(board, color);
    }

    public IPiece makeKing(Board board, ColorEnum color) {
        return new King(board, color);
    }
}
