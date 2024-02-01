package com.vini.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vini.game.gamemodes.SoloGameMode;
import com.vini.game.interfaces.IGameMode;
import com.vini.socket.dtos.MovementDTO;
import com.vini.socket.models.GameModel;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

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
        IGameMode game = new SoloGameMode(connection);
        GameModel.INSTANCE = game;
    }

    @Override
    public void onMessage(WebSocket connection, String message) {
        try {
            ObjectMapper objMapper = new ObjectMapper();
            MovementDTO movement = objMapper.readValue(message, MovementDTO.class);
            GameModel.INSTANCE.move(movement.getFrom(), movement.getTo());
        } catch (JsonProcessingException e) {
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
        connection.close(1013);
    }

    private Runnable shutdown() {
        return () -> {
            this.getConnections().forEach(socket -> socket.close(1013));
            while (this.getConnections().stream().anyMatch(c -> !c.isClosed())) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
    }
}
