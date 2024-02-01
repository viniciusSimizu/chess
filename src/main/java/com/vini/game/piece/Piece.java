package com.vini.game.piece;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.lib.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece implements IPiece {
    protected Board board;

    protected ColorEnum color;
    protected final Position position;
    protected List<List<Boolean>> moves;

    protected Piece(Board board, ColorEnum color) {
        this.board = board;
        this.color = color;
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
    public boolean tryMove(Position to) {
        if (!this.canMove(to)) {
            return false;
        }

        this.board.tryMovePiece(to, this.position());
        this.board.tryMovePiece(this.position, null);
        this.position.x = to.x;
        this.position.y = to.y;

        this.resetMoves();

        return true;
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
    public ColorEnum color() {
        return this.color;
    }

    @Override
    public Position position() {
        return this.position;
    }

    protected boolean canMove(Position position) {
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
