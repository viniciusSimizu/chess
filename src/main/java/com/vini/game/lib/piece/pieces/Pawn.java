package com.vini.game.lib.piece.pieces;

import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.PieceEnum;
import com.vini.game.interfaces.IBoardSquareLayer;
import com.vini.game.lib.board.BoardSquareLayer;
import com.vini.game.lib.piece.Piece;
import com.vini.game.structs.Position;

public class Pawn extends Piece {

    private static final int[][] forkDirections = {{-1, 1}, {1, 1}};
    private int directionWeight = 0;

    private int movedTwoRound = -1;
    private boolean isFirstMoveFlag = true;

    @Override
    public void setColor(ColorEnum color) {
        super.setColor(color);

        if (color == ColorEnum.WHITE) {
            this.directionWeight = -1;
        } else {
            this.directionWeight = 1;
        }
    }

    @Override
    public void appendToBoard() {
        var localPosition = new Position();
        int maxDistance = 2;
        IBoardSquareLayer prevLayer = null;

        for (int range = 1; range <= maxDistance; range++) {
            localPosition.update(this.position);
            localPosition.y += range * this.directionWeight;

            if (!this.isInsideTable(localPosition)) {
                break;
            }

            var square = this.board.findSquare(localPosition);

            IBoardSquareLayer newLayer = new BoardSquareLayer(square);
            newLayer.setOwnedBy(this);
            newLayer.setCanCatchEnemy(false);

            if (prevLayer != null) {
                newLayer.setPrev(prevLayer);
                prevLayer.setNext(newLayer);
            } else {
                this.square.addAttackHeadLayer(newLayer);
            }

            square.addChildrenLayer(newLayer);
            prevLayer = newLayer;
        }

        for (int[] forkDirection : forkDirections) {
            localPosition.update(this.position);
            localPosition.x += forkDirection[0];
            localPosition.y += forkDirection[1] * this.directionWeight;

            if (!this.isInsideTable(localPosition)) {
                continue;
            }

            var square = this.board.findSquare(localPosition);
            var target = square.getOccupiedBy();

            IBoardSquareLayer newLayer = new BoardSquareLayer(square);
            newLayer.setOwnedBy(this);
            newLayer.setCanCatchEnemy(true);

            if (this.isEnemy(target)) {
                newLayer.setTarget(target);
            }

            square.addChildrenLayer(newLayer);
            this.square.addAttackHeadLayer(newLayer);
        }
    }

    @Override
    public boolean tryMove(Position to) {
        boolean tryingToMoveTwo = Math.abs(this.position.y - to.y) == 2;
        boolean successMove = super.tryMove(to);

        if (!successMove) {
            return false;
        }

        this.isFirstMoveFlag = false;
        if (tryingToMoveTwo) {
            var game = this.board.getGame();
            this.movedTwoRound = game.getRound();
        }

        return true;
    }

    @Override
    public PieceEnum getType() {
        return PieceEnum.PAWN;
    }

    public int getMovedTwoRound() {
        return this.movedTwoRound;
    }

    public void setMovedTwoRound(int movedTwoRound) {
        this.movedTwoRound = movedTwoRound;
    }

    public boolean getIsFirstMoveFlag() {
        return this.isFirstMoveFlag;
    }

    public void setIsFirstMoveFlag(boolean isFirstMoveFlag) {
        this.isFirstMoveFlag = isFirstMoveFlag;
    }
}
