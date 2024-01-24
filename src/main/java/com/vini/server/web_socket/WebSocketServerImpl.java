package com.vini.server.web_socket;

import com.vini.shared.Env;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class WebSocketServerImpl extends WebSocketServer {

    private static int SOCKET_PORT = Integer.parseInt(Env.get("SOCKET_PORT"));

    public WebSocketServerImpl() {
        super(new InetSocketAddress(WebSocketServerImpl.SOCKET_PORT));
    }

    @Override
    public void onStart() {
        System.out.println("onStart");
    }

    @Override
    public void onOpen(WebSocket connection, ClientHandshake handshake) {
        connection.send("Connected");
        System.out.println("onOpen");
    }

    @Override
    public void onMessage(WebSocket connection, String message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onMessage'");
    }

    @Override
    public void onClose(WebSocket connection, int code, String reason, boolean remote) {
        System.out.println("onClose");
    }

    @Override
    public void onError(WebSocket connection, Exception exception) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onError'");
    }
}
