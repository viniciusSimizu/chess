package com.vini.game.interfaces;

import com.vini.game.lib.Position;

import java.util.List;

public interface IGameMode {

    void move(Position from, Position to);

    List<List<String>> export();
}
