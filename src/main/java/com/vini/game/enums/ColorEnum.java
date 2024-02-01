package com.vini.game.enums;

public enum ColorEnum {
  BLACK,
  WHITE;

  @Override
  public String toString() {
    switch (this) {
    case WHITE:
      return "white";
    case BLACK:
      return "black";
    default:
      return null;
    }
  }
}
