package com.vini.game.piece;

import com.vini.game.enums.ColorEnum;
import com.vini.game.lib.Position;

import java.util.List;

public interface IPiece {
    public void resetMoves();

    public IPiece structureMoves();

    public boolean tryMove(Position position);

    public IPiece updateMoves();

    public ColorEnum color();

    public Position position();

    public List<List<Boolean>> moves();

    public String getIdentifier();
}
