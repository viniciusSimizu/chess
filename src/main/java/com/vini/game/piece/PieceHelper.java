package com.vini.game.piece;

import com.vini.game.interfaces.IPiece;

public class PieceHelper {
  public static boolean isAlly(IPiece source, IPiece target) {
    if (source == null || target == null) {
      return false;
    }

    return source.getColor() == target.getColor();
  }

  public static boolean isEnemy(IPiece source, IPiece target) {
    if (source == null || target == null) {
      return false;
    }

    return source.getColor() != target.getColor();
  }
}
