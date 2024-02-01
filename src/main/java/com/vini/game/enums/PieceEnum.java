package com.vini.game.enums;

public enum PieceEnum {
  PAWN,
  ROOK,
  KNIGHT,
  BISHOP,
  QUEEN,
  KING;

  @Override
  public String toString() {
    switch (this) {
    case PAWN:
      return "pawn";
    case ROOK:
      return "rook";
    case KNIGHT:
      return "knight";
    case BISHOP:
      return "bishop";
    case QUEEN:
      return "queen";
    case KING:
      return "king";
    default:
      return null;
    }
  }
}
