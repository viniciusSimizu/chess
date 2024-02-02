package com.vini.game.piece;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece implements IPiece {

    protected Board board;
    protected ColorEnum color;
    protected List<List<Boolean>> moves;
    protected final Position position;

    protected Piece() {
        this.position = new Position(null, null);
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;

        this.moves = new ArrayList<>();
        for (int i = 0; i < this.board.getHeight(); i++) {
            List<Boolean> row = new ArrayList<>();
            for (int j = 0; j < this.board.getWidth(); j++) {
                row.add(false);
            }
            this.moves.add(row);
        }
    }

    @Override
    public void resetMoves() {
        for (int i = 0; i < this.getMoves().size(); i++) {
            for (int j = 0; j < this.getMoves().get(i).size(); j++) {
                this.getMoves().get(i).set(j, false);
            }
        }
    }

    @Override
    public void setColor(ColorEnum color) {
        this.color = color;
    }

    @Override
    public boolean tryMove(Position to) {
        if (!this.canMove(to)) {
            return false;
        }

        this.board.tryMovePiece(to, this.getPosition());
        this.board.tryMovePiece(this.position, null);
        this.position.x = to.x;
        this.position.y = to.y;
        this.resetMoves();

        return true;
    }

    @Override
    public ColorEnum getColor() {
        return this.color;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public List<List<Boolean>> getMoves() {
        return this.moves;
    }

    protected boolean canMove(Position position) {
        if (!this.isInsideMoves(position)) {
            return false;
        }
        return this.getMoves().get(position.y).get(position.x);
    }

    protected boolean isInsideMoves(Position position) {
        if (position.y < 0 || position.y >= this.getMoves().size()) {
            return false;
        }

        if (position.x >= 0 && position.x < this.getMoves().get(position.y).size()) {
            return true;
        }

        return false;
    }
}
