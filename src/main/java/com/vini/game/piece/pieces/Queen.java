package com.vini.game.piece.pieces;

import com.vini.game.board.Board;
import com.vini.game.enums.PieceEnum;
import com.vini.game.piece.IPiece;
import com.vini.game.piece.Piece;
import com.vini.game.piece.PieceHelper;

public class Queen extends Piece {
  private final int[][] directions = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0},
                                      {1, 0},   {-1, 1}, {0, 1},  {1, 1}};

  public Queen(Board board) { super(board); }

  @Override
  public IPiece updateMoves() {
    for (int[] direction : this.directions) {
      int[] position = this.position().clone();

      while (true) {
        position[0] += direction[0];
        position[1] += direction[1];

        if (!board.isInsideTable(position)) {
          break;
        };

        IPiece target = board.findPiece(position);

        if (PieceHelper.isAlly(this, target)) {
          break;
        }

        this.moves().get(position[1]).set(position[0], true);

        if (PieceHelper.isEnemy(this, target)) {
          break;
        }
      }
    }

    return this;
  }

  @Override
  public PieceEnum fen() {
    return PieceEnum.QUEEN;
  }
}
