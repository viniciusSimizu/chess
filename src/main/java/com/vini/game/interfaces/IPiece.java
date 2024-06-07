package com.vini.game.interfaces;

import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.structs.Position;

import java.util.List;

public interface IPiece {

    public boolean canMove(Position to);

    public boolean tryMove(Position to);

    public void updateBoard();

    public String exportIdentifier();

    public List<Boolean> exportMoves();

    public IBoardSquare getSquare();

    public Position getPosition();

    public PieceEnum getType();

    public ColorEnum getColor();

    public void setColor(ColorEnum color);

    public void setSquare(IBoardSquare square);
}
