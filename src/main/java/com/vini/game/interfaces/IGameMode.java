package com.vini.game.interfaces;

import com.vini.game.board.Board;
import com.vini.game.lib.Position;

public interface IGameMode {

    void move(Position from, Position to);

    Board getBoard();
}
