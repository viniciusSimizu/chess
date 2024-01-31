package com.vini.game.interfaces;

import com.vini.game.lib.Position;

import java.util.UUID;

public interface IGameMode {

    boolean hasPermissionToMove(UUID userId);

    boolean tryMove(UUID userId, Position from, Position to);

    void toggleColor();

    void won(UUID userId);

    void draw();
}
