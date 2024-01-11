package com.vini.game.board;

import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.piece.IPiece;
import com.vini.game.piece.PieceFactory;
import java.util.ArrayList;
import java.util.List;

public class BoardBuilder {
  private Board result;

  private List<List<IPiece>> table = new ArrayList<>();
  private List<IPiece> row = new ArrayList<>();

  private PieceFactory factory = PieceFactory.getInstance();

  private int rowIdx = 0;
  private int colIdx = 0;

  public BoardBuilder() { this.result = new Board(this.table); }

  public BoardBuilder buildRow() {
    this.table.add(this.row);
    this.row = new ArrayList<>();

    this.rowIdx++;
    this.colIdx = 0;
    return this;
  }

  public BoardBuilder buildEmptySquare() {
    this.row.add(null);
    this.colIdx++;
    return this;
  }

  public BoardBuilder buildPiece(PieceEnum piece, ColorEnum color) {
    IPiece buildingPiece;
    switch (piece) {
    case PAWN:
      buildingPiece = this.factory.makePawn(this.result());
      break;
    case ROOK:
      buildingPiece = this.factory.makeRook(this.result());
      break;
    case KNIGHT:
      buildingPiece = this.factory.makeKnight(this.result());
      break;
    case BISHOP:
      buildingPiece = this.factory.makeBishop(this.result());
      break;
    case QUEEN:
      buildingPiece = this.factory.makeQueen(this.result());
      break;
    case KING:
      buildingPiece = this.factory.makeKing(this.result());
      break;
    default:
      return this;
    }

    buildingPiece.setPosition(new int[] {this.colIdx, this.rowIdx});
    buildingPiece.setColor(color);

    this.row.add(buildingPiece);
    this.colIdx++;
    return this;
  }

  public Board result() { return this.result; }
}
