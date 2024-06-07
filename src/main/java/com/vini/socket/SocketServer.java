package com.vini.socket;

import com.vini.socket.enums.MessageTypeEnum;
import com.vini.socket.lib.RenderBoard;
import com.vini.socket.lib.RequestHandler;
import com.vini.socket.models.GameModel;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SocketServer extends WebSocketServer {

    public static WebSocketServer server;

    public SocketServer(int socketPort) {
        super(new InetSocketAddress(socketPort));
    }

    @Override
    public void onStart() {
        server = this;
        Runtime.getRuntime().addShutdownHook(new Thread(this.shutdown()));
        System.out.println("Socket is running");
    }

    @Override
    public void onOpen(WebSocket connection, ClientHandshake handshake) {
        try {
            var type = MessageTypeEnum.CONNECTION;
            var game = GameModel.getInstance();

            String boardState = RenderBoard.render(type, game);
            connection.send(boardState);

        } catch (IOException e) {
            e.printStackTrace();
            connection.close(3001);
        }
    }

    @Override
    public void onMessage(WebSocket connection, String message) {
        RequestHandler.handle(connection, message);
    }

    @Override
    public void onClose(WebSocket connection, int code, String reason, boolean remote) {
        System.out.println("onClose");
    }

    @Override
    public void onError(WebSocket connection, Exception exception) {
        System.out.println("onError");
        System.out.println(exception.getMessage());
        exception.printStackTrace();
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
