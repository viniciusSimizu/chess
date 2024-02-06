package com.vini.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vini.socket.enums.MessageType;
import com.vini.socket.handlers.RenderBoardHandler;
import com.vini.socket.lib.Movement;
import com.vini.socket.models.GameModel;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SocketServer extends WebSocketServer {

    public SocketServer(int socketPort) {
        super(new InetSocketAddress(socketPort));
    }

    @Override
    public void onStart() {
        Runtime.getRuntime().addShutdownHook(new Thread(this.shutdown()));
        System.out.println("Socket is running");
    }

    @Override
    public void onOpen(WebSocket connection, ClientHandshake handshake) {
        try {
            var type = MessageType.CONNECTION;
            var game = GameModel.INSTANCE;

            String boardState = RenderBoardHandler.render(type, game);
            connection.send(boardState);

        } catch (IOException e) {
            e.printStackTrace();
            connection.close(3001);
        }
    }

    @Override
    public void onMessage(WebSocket connection, String message) {
        var objMapper = new ObjectMapper();
        Movement movement;

        try {
            movement = objMapper.readValue(message, Movement.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        boolean hasMoved = GameModel.INSTANCE.tryMove(movement.from, movement.to);
        if (!hasMoved) {
            return;
        }

        try {
            var type = MessageType.RELOAD;
            var game = GameModel.INSTANCE;

            this.broadcast(RenderBoardHandler.render(type, game));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(WebSocket connection, int code, String reason, boolean remote) {
        System.out.println("onClose");
    }

    @Override
    public void onError(WebSocket connection, Exception exception) {
        System.out.println("onError");
        System.out.println(exception.getMessage());
        if (connection != null) {
            connection.close(1001);
        }
    }

    private Runnable shutdown() {
        return () -> {
            this.getConnections().forEach(socket -> socket.close(1001));

            var allClosed = false;
            do {
                allClosed = !this.getConnections().stream().anyMatch(c -> !c.isClosed());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!allClosed);
        };
    }
}
