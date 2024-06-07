package com.vini.socket.models;

import com.vini.game.fen.Fen;
import com.vini.game.gamemodes.SoloGameMode;
import com.vini.game.interfaces.IGame;

public class GameModel {

    private static IGame instance;

    static {
        var board = Fen.build(Fen.defaultNotation);
        instance = new SoloGameMode(board);
    }

    private GameModel() {}

    public static IGame getInstance() {
        return instance;
    }
}
