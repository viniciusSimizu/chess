package com.vini.game.piece;

import com.vini.game.enums.ColorEnum;
import com.vini.game.interfaces.IPiece;
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

    public IPiece makePawn(ColorEnum color) {
        Pawn pawn = new Pawn();
        pawn.setColor(color);
        return pawn;
    }

    public IPiece makeRook(ColorEnum color) {
        Rook rook = new Rook();
        rook.setColor(color);
        return rook;
    }

    public IPiece makeKnight(ColorEnum color) {
        Knight knight = new Knight();
        knight.setColor(color);
        return knight;
    }

    public IPiece makeBishop(ColorEnum color) {
        Bishop bishop = new Bishop();
        bishop.setColor(color);
        return bishop;
    }

    public IPiece makeQueen(ColorEnum color) {
        Queen queen = new Queen();
        queen.setColor(color);
        return queen;
    }

    public IPiece makeKing(ColorEnum color) {
        King king = new King();
        king.setColor(color);
        return king;
    }
}
