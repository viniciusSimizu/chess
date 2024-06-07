package com.vini.game.lib.piece;

import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.GameStateEnum;
import com.vini.game.interfaces.IBoard;
import com.vini.game.interfaces.IBoardSquare;
import com.vini.game.interfaces.IPiece;
import com.vini.game.structs.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Piece implements IPiece {

    protected IBoardSquare square;
    protected IBoard board;
    protected ColorEnum color;
    private List<Boolean> moves;

    @Override
    public boolean tryMove(Position to) {
        if (!this.canMove(to)) {
            return false;
        }

        var targetSquare = this.board.findSquare(to);
        this.square.dropAttackHeadLayers();
        this.square.setOccupiedBy(null);

        if (targetSquare.getOccupiedBy() != null) {
            targetSquare.dropAttackHeadLayers();
        }
        targetSquare.setOccupiedBy(this);
        this.updateBoard();

        return true;
    }

    @Override
    public String exportIdentifier() {
        String type = this.getType().toString();
        String color = this.getColor().toString();
        return String.join(" ", type, color);
    }

    @Override
    public IBoardSquare getSquare() {
        return this.square;
    }

    @Override
    public Position getPosition() {
        return this.square.getPosition();
    }

    @Override
    public List<Boolean> exportMoves() {
        return this.moves;
    }

    @Override
    public ColorEnum getColor() {
        return this.color;
    }

    @Override
    public void setColor(ColorEnum color) {
        this.color = color;
    }

    @Override
    public void setSquare(IBoardSquare square) {
        this.square = square;
        this.board = square.getBoard();
        var size = this.board.getHeight() * this.board.getWidth();
        this.moves = new ArrayList<>(Collections.nCopies(size, false));
    }

    protected boolean isInsideTable(Position position) {
        if (position.x == null || position.y == null) {
            return false;
        }

        if (position.x < 0 || position.x >= this.board.getWidth()) {
            return false;
        }

        if (position.y < 0 || position.y >= this.board.getHeight()) {
            return false;
        }

        return true;
    }

    protected void resetMoves() {
        for (int i = 0; i < this.moves.size(); i++) {
            this.moves.set(i, false);
        }
    }

    protected boolean isInCheck() {
        var game = this.board.getGame();
        var inCheck = game.getState() == GameStateEnum.CHECK;
        var sameColor = game.getCurrColor() == this.color;
        return inCheck && sameColor;
    }

    protected boolean isEnemy(IPiece piece) {
        if (piece == null) {
            return false;
        }
        return this.color != piece.getColor();
    }

    protected boolean isAlly(IPiece piece) {
        if (piece == null) {
            return false;
        }
        return this.color == piece.getColor();
    }
}
