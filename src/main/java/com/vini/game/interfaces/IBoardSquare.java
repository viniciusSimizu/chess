package com.vini.game.interfaces;

import com.vini.game.structs.Position;

import java.util.List;

public interface IBoardSquare {

    IPiece getOccupiedBy();

    IBoard getBoard();

    Position getPosition();

    List<IBoardSquareLayer> getChildrenLayers();

    List<IBoardSquareLayer> getAttackHeadLayers();

    void addChildrenLayer(IBoardSquareLayer layer);

    void addAttackHeadLayer(IBoardSquareLayer layer);

    void setOccupiedBy(IPiece piece);

    void dropAttackHeadLayers();
}
