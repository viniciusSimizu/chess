package com.vini.game.interfaces;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.lib.Position;

import java.util.List;

public interface IPiece {

    public void resetMoves();

		public void setColor(ColorEnum color);

		public void setBoard(Board board);

    public boolean tryMove(Position position);

    public String getIdentifier();

    public ColorEnum getColor();

    public Position getPosition();

    public IPiece updateMoves();

    public List<List<Boolean>> getMoves();
}
