package com.vini.game.piece;

import com.vini.game.Game;
import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import java.util.List;

public interface IPiece {
  public void resetMoves();
  public IPiece structureMoves();
  public IPiece updateMoves();

  public IPiece setGame(Game game);

  public void move(int[] position);
  public boolean canMove(int[] position);

  public ColorEnum color();
  public IPiece setColor(ColorEnum color);

  public int[] position();
  public IPiece setPosition(int[] position);

  public List<List<Boolean>> moves();
  public IPiece setMoves(List<List<Boolean>> moves);

  public PieceEnum fen();
}
