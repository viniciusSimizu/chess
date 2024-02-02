package com.vini.game.interfaces;

import com.vini.game.lib.Position;
import com.vini.socket.lib.TableRepresentation;

public interface IGameMode {

    void move(Position from, Position to);

    TableRepresentation export();
}
