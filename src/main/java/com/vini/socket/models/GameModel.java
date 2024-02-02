package com.vini.socket.models;

import com.vini.game.gamemodes.SoloGameMode;
import com.vini.game.interfaces.IGameMode;

public class GameModel {

    public static IGameMode INSTANCE = new SoloGameMode();

    private GameModel() {}
}
