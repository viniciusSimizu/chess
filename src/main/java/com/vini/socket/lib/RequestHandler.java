package com.vini.socket.lib;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vini.socket.SocketServer;
import com.vini.socket.enums.MessageTypeEnum;
import com.vini.socket.models.GameModel;
import com.vini.socket.structs.Movement;

import org.java_websocket.WebSocket;

import java.io.IOException;

public class RequestHandler {

    public static void handle(WebSocket connection, String message) {
        Movement movement;
        try {
            movement = JsonHandler.readValue(message, Movement.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }
        var game = GameModel.getInstance();

        boolean hasMoved = game.tryMove(movement.from, movement.to);
        if (!hasMoved) {
            return;
        }

        game.nextTurn();

        try {
            var type = MessageTypeEnum.RELOAD;
            SocketServer.server.broadcast(RenderBoard.render(type, game));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
