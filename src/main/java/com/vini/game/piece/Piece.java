package com.vini.game.piece;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.lib.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece implements IPiece {
    protected Board board;

    private ColorEnum color;
    private final Position position;
    private List<List<Boolean>> moves;

    protected Piece(Board board) {
        this.board = board;
        this.position = new Position(null, null);
    }

    @Override
    public IPiece structureMoves() {
        this.moves = new ArrayList<>();

        for (int i = 0; i < this.board.getHeight(); i++) {
            List<Boolean> row = new ArrayList<>();
            for (int j = 0; j < this.board.getWidth(); j++) {
                row.add(false);
            }
            this.moves.add(row);
        }

        return this;
    }

    @Override
    public void resetMoves() {
        for (int i = 0; i < this.moves().size(); i++) {
            for (int j = 0; j < this.moves().get(i).size(); j++) {
                this.moves().get(i).set(j, false);
            }
        }
        ;
    }

    @Override
    public IPiece updateMoves() {
        if (this.moves.size() == 0) {
            this.structureMoves();
        }

        this.resetMoves();
        return this;
    }

    @Override
    public ColorEnum color() {
        return this.color;
    }

    @Override
    public IPiece setColor(ColorEnum color) {
        this.color = color;
        return this;
    }

    @Override
    public Position position() {
        return this.position;
    }

    @Override
    public boolean canMove(Position position) {
        if (!this.isInsideMoves(position)) {
            return false;
        }
        return this.moves().get(position.y).get(position.x);
    }

    @Override
    public List<List<Boolean>> moves() {
        return this.moves;
    }

    protected boolean isInsideMoves(Position position) {
        if (position.y < 0 || position.y >= this.moves().size()) {
            return false;
        }

        if (position.x >= 0 && position.x < this.moves().get(position.y).size()) {
            return true;
        }

        return false;
    }
}
