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
    protected List<Boolean> moves;
    protected final Position position = new Position(null, null);

    @Override
    public void setBoard(Board board) {
        this.board = board;
        this.moves = new ArrayList<>(board.getHeight() * board.getWidth());

        for (int i = 0; i < board.getHeight() * board.getWidth(); i++) {
            this.moves.add(false);
        }
    }

    @Override
    public void resetMoves() {
        for (int i = 0; i < this.moves.size(); i++) {
            this.moves.set(i, false);
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
    public void setPosition(Position position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    @Override
    public List<Boolean> getMoves() {
        return this.moves;
    }

    @Override
    public String getIdentifiers() {
        String type = this.getType().toString();
        String color = this.getColor().toString();
        return String.join(" ", type, color);
    }

    protected boolean canMove(Position position) {
        if (!this.board.isInsideTable(position)) {
            return false;
        }
        return this.moves.get(this.board.calcPositionIndex(position));
    }

    protected boolean isAlly(IPiece target) {
        if (target == null) {
            return false;
        }

        return this.color == target.getColor();
    }

    protected boolean isEnemy(IPiece target) {
        if (target == null) {
            return false;
        }

        return this.color != target.getColor();
    }
}
