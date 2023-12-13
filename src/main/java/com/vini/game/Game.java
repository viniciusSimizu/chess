package com.vini.game;

import com.vini.game.board.Board;
import com.vini.game.board.iterator.BoardIteratorOverPiece;
import com.vini.game.enums.ColorEnum;
import com.vini.game.piece.IPiece;

public class Game {

  private ColorEnum turn;
  private final Board board;

  public Game(Board board, ColorEnum turn) {
    this.board = board;
    this.turn = turn;
  }

  public boolean move(int[] sourcePosition, int[] targetPosition) {
    IPiece piece = this.board.findPiece(sourcePosition);

    if (piece == null) {
      return false;
    }

    if (!piece.canMove(targetPosition)) {
      return false;
    }

    piece.move(targetPosition);

    return true;
  }

  public void toggleTurn() {
    if (this.turn == ColorEnum.BLACK) {
      this.turn = ColorEnum.WHITE;
    } else {
      this.turn = ColorEnum.BLACK;
    }
  }

  public void refreshPieceMoves() {
    BoardIteratorOverPiece iterator = new BoardIteratorOverPiece(this.board);

    while (iterator.hasNext()) {
      IPiece piece = iterator.next();
      piece.resetMoves();
      piece.updateMoves();
    }
  }

  public void attachGameOverPieces() {
    BoardIteratorOverPiece iterator = new BoardIteratorOverPiece(this.board);

    while (iterator.hasNext()) {
      IPiece piece = iterator.next();
      piece.setGame(this);
    }
  }
}
