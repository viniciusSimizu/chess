package com.vini.game.interfaces;

import com.vini.game.board.Board;
import com.vini.game.enums.ColorEnum;
import com.vini.game.lib.Position;

public interface IGameMode {

    boolean tryMove(Position from, Position to);

    Board getBoard();

    ColorEnum getCurrColor();
}
