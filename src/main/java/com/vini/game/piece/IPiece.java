package com.vini.game.piece;

import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.lib.Position;

import java.util.List;

public interface IPiece {
    public void resetMoves();

    public IPiece structureMoves();

    public IPiece updateMoves();

    public ColorEnum color();

    public IPiece setColor(ColorEnum color);

    public Position position();

		public boolean canMove(Position position);

    public List<List<Boolean>> moves();

    public PieceEnum fen();
}
