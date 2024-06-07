package com.vini.game.interfaces;

import com.vini.game.structs.Position;

public interface IBoard {

    void addPiece(IPiece piece);

    IBoardSquare findSquare(Position position);

    void updateAllPieces();

    Integer getHeight();

    Integer getWidth();

    IGame getGame();

    void setGame(IGame game);

    void setDimensions(int width, int height);
}
