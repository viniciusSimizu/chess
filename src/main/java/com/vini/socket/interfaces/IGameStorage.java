package com.vini.socket.interfaces;

import com.vini.game.Game;

import java.util.UUID;

public interface IGameStorage {

    Game findGame(UUID user);
}
