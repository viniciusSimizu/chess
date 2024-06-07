package com.vini.game.lib.board;

import com.vini.game.interfaces.IBoard;
import com.vini.game.interfaces.IBoardSquare;
import com.vini.game.interfaces.IBoardSquareLayer;
import com.vini.game.interfaces.IPiece;
import com.vini.game.structs.Position;

import java.util.LinkedList;
import java.util.List;

public class BoardSquare implements IBoardSquare {

    private final IBoard board;
    private final Position position;
    private final List<IBoardSquareLayer> childrenLayers = new LinkedList<>();
    private final List<IBoardSquareLayer> attackHeadLayers = new LinkedList<>();

    private IPiece occupiedBy;

    public BoardSquare(IBoard board, int index) {
        this.board = board;
        this.position = Position.fromIndex(index, this.board.getWidth());
    }

    @Override
    public IPiece getOccupiedBy() {
        return this.occupiedBy;
    }

    @Override
    public IBoard getBoard() {
        return this.board;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public List<IBoardSquareLayer> getChildrenLayers() {
        return this.childrenLayers;
    }

    @Override
    public List<IBoardSquareLayer> getAttackHeadLayers() {
        return this.attackHeadLayers;
    }

    @Override
    public void addChildrenLayer(IBoardSquareLayer layer) {
        this.childrenLayers.add(layer);
    }

    @Override
    public void addAttackHeadLayer(IBoardSquareLayer layer) {
        this.attackHeadLayers.add(layer);
    }

    @Override
    public void setOccupiedBy(IPiece piece) {
        this.occupiedBy = piece;
    }

    @Override
    public void dropAttackHeadLayers() {
        this.attackHeadLayers.clear();
    }
}
