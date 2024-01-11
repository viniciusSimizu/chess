package com.vini.game;

import com.vini.game.board.Board;
import com.vini.game.board.iterator.BoardIteratorOverPiece;
import com.vini.game.enums.ColorEnum;
import com.vini.game.piece.IPiece;
import com.vini.server.socket.PlayerData;
import java.util.EnumMap;

public class Game {

  private ColorEnum turn;
  private final Board board;
  private final EnumMap<ColorEnum, PlayerData> players;

  public Game(Board board, ColorEnum turn,
              EnumMap<ColorEnum, PlayerData> players) {
    this.board = board;
    this.turn = turn;
    this.players = players;
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
