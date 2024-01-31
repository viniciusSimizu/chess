package com.vini.game.lib;

import com.vini.game.enums.ColorEnum;

import java.util.UUID;

import org.java_websocket.WebSocket;

public class PlayerData {

    private UUID id;
    private ColorEnum color;
		private WebSocket socket;
}
