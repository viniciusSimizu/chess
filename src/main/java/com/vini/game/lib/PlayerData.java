package com.vini.game.lib;


import org.java_websocket.WebSocket;

import java.util.UUID;

public class PlayerData {

    private UUID id = UUID.randomUUID();
    private WebSocket socket;

    public PlayerData(WebSocket socket) {
        this.socket = socket;
    }

    public UUID getId() {
        return id;
    }

    public WebSocket getSocket() {
        return socket;
    }
}
