package com.vini;

import com.sun.net.httpserver.HttpServer;
import com.vini.server.web.WebServer;
import com.vini.server.web.routes.Routes;
import com.vini.server.web_socket.WebSocketServerImpl;

import org.java_websocket.server.WebSocketServer;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        App.startWeb();
        App.startWebSocket();
    }

    private static void startWeb() throws IOException {
        HttpServer webServer = WebServer.createServer();
        Routes.registerRoutes(webServer);
        webServer.setExecutor(null);

        webServer.start();

        System.out.println("Server is running");
    }

    private static void startWebSocket() throws IOException {
        WebSocketServer webSocketServer = new WebSocketServerImpl();
        webSocketServer.start();
    }
}
