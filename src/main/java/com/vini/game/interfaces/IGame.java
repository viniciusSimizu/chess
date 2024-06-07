package com.vini.game.interfaces;

import com.vini.game.enums.ColorEnum;
import com.vini.game.enums.GameStateEnum;
import com.vini.game.structs.Position;
import com.vini.socket.structs.TableRepresentation;

public interface IGame {

    boolean tryMove(Position from, Position to);

    void nextTurn();

    ColorEnum getCurrColor();

    GameStateEnum getState();

    int getRound();

    TableRepresentation export();
}
