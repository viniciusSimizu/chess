package com.vini.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vini.socket.lib.Movement;
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
        System.out.println("onOpen");
    }

    @Override
    public void onMessage(WebSocket connection, String message) {
        try {
            ObjectMapper objMapper = new ObjectMapper();
            Movement movement = objMapper.readValue(message, Movement.class);
            GameModel.INSTANCE.move(movement.getFrom(), movement.getTo());

            this.broadcast("RELOAD");
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
        if (connection != null) {
            connection.close(1013);
        }
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
