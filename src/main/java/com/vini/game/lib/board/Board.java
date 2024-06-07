package com.vini.game.lib.board;

import com.vini.game.interfaces.IBoard;
import com.vini.game.interfaces.IBoardSquare;
import com.vini.game.interfaces.IGame;
import com.vini.game.interfaces.IPiece;
import com.vini.game.lib.board.iterators.BoardIteratorOverPiece;
import com.vini.game.structs.Position;

import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard {

    private Integer width, height;
    private IGame game;
    private List<IBoardSquare> squares;

    @Override
    public void addPiece(IPiece piece) {
        var position = piece.getPosition();
        var square = this.findSquare(position);

        if (square == null) {
            throw new Error("Piece out of Board range");
        }

        square.setOccupiedBy(piece);
        piece.setSquare(square);
    }

    @Override
    public IBoardSquare findSquare(Position position) {
        var index = position.getIndex(this.width);
        return this.squares.get(index);
    }

    @Override
    public void updateAllPieces() {
        var iterator = new BoardIteratorOverPiece(this);
        while (iterator.hasNext()) {
            var piece = iterator.next();
            piece.updateBoard();
        }
    }

    @Override
    public Integer getHeight() {
        return this.height;
    }

    @Override
    public Integer getWidth() {
        return this.width;
    }

    @Override
    public IGame getGame() {
        return this.game;
    }

    @Override
    public void setGame(IGame game) {
        this.game = game;
    }

    @Override
    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;

        var size = width * height;
        this.squares = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            var square = new BoardSquare(this, i);
            this.squares.add(square);
        }
    }
}
