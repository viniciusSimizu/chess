package com.vini.game.interfaces;

public interface IBoardSquareLayer {

    IBoardSquare getSquare();

    IBoardSquareLayer getNext();

    IBoardSquareLayer getPrev();

    IPiece getTarget();

    IPiece getOwnedBy();

    boolean getCanCatchEnemy();

    void setNext(IBoardSquareLayer layer);

    void setPrev(IBoardSquareLayer layer);

    void setTarget(IPiece piece);

    void setOwnedBy(IPiece piece);

    void setCanCatchEnemy(boolean canCatchEnemy);
}
