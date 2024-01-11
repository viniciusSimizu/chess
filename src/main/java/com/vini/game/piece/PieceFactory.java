package com.vini.game.piece;

import com.vini.game.board.Board;
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

  public IPiece makePawn(Board board) { return new Pawn(board); }

  public IPiece makeRook(Board board) { return new Rook(board); }

  public IPiece makeKnight(Board board) { return new Knight(board); }

  public IPiece makeBishop(Board board) { return new Bishop(board); }

  public IPiece makeQueen(Board board) { return new Queen(board); }

  public IPiece makeKing(Board board) { return new King(board); }
}
