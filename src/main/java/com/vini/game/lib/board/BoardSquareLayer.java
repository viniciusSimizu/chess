package com.vini.game.lib.board;

import com.vini.game.interfaces.IBoardSquare;
import com.vini.game.interfaces.IBoardSquareLayer;
import com.vini.game.interfaces.IPiece;

public class BoardSquareLayer implements IBoardSquareLayer {

    private final IBoardSquare square;

    private IBoardSquareLayer prev, next;
    private IPiece ownedBy;
    private IPiece target;
    private boolean canCatchEnemy;

    public BoardSquareLayer(IBoardSquare square) {
        this.square = square;
    }

    @Override
    public IBoardSquare getSquare() {
        return this.square;
    }

    @Override
    public IBoardSquareLayer getNext() {
        return this.next;
    }

    @Override
    public IBoardSquareLayer getPrev() {
        return this.prev;
    }

    @Override
    public IPiece getTarget() {
        return this.target;
    }

    @Override
    public IPiece getOwnedBy() {
        return this.ownedBy;
    }

    @Override
    public boolean getCanCatchEnemy() {
        return this.canCatchEnemy;
    }

    @Override
    public void setNext(IBoardSquareLayer layer) {
        this.next = layer;
    }

    @Override
    public void setPrev(IBoardSquareLayer layer) {
        this.prev = layer;
    }

    @Override
    public void setTarget(IPiece piece) {
        this.target = piece;
    }

    @Override
    public void setOwnedBy(IPiece piece) {
        this.ownedBy = piece;
    }

    @Override
    public void setCanCatchEnemy(boolean canCatchEnemy) {
        this.canCatchEnemy = canCatchEnemy;
    }
}
