package com.vini.socket;

import com.vini.socket.lib.GameManager;
import com.vini.socket.lib.MessageQueue;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class SocketServer extends WebSocketServer {

    private static int MAX_CONNECTIONS = 16;

    private List<WebSocket> connections = new ArrayList<>(MAX_CONNECTIONS);
    private MessageQueue messageQueue;

    public SocketServer(int socketPort) {
        super(new InetSocketAddress(socketPort));
    }

    @Override
    public void onStart() {
        this.messageQueue = new MessageQueue();
        Runnable matchMakingRunnable = new GameManager(this.messageQueue);
        Thread matchMakingThread = new Thread(matchMakingRunnable);
        matchMakingThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(this.shutdown()));
        System.out.println("Socket is running");
    }

    @Override
    public void onOpen(WebSocket connection, ClientHandshake handshake) {
        this.connections.add(connection);
        System.out.println("onOpen");
    }

    @Override
    public void onMessage(WebSocket connection, String message) {
        this.messageQueue.put(connection, message);
        System.out.println("onMessage");
    }

    @Override
    public void onClose(WebSocket connection, int code, String reason, boolean remote) {
        this.connections.remove(connection);
        System.out.println("onClose");
    }

    @Override
    public void onError(WebSocket connection, Exception exception) {
        this.connections.remove(connection);
        System.out.println("onError");
    }

    private Runnable shutdown() {
        return () -> {
            this.connections.forEach(socket -> socket.close(1013));
        };
    }
}
