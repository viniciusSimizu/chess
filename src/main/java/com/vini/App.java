package com.vini;

import com.sun.net.httpserver.HttpServer;
import com.vini.shared.Env;
import com.vini.socket.SocketServer;
import com.vini.web.WebServer;
import com.vini.web.routes.Routes;

import org.java_websocket.server.WebSocketServer;

import java.io.IOException;

public class App {

    private static int HTTP_PORT, SOCKET_PORT, BACKLOGS;

    public static void main(String[] args) {
        loadEnv();

        try {
            startWeb();
            startSocket();
        } catch (IOException e) {
            System.exit(0);
        }
    }

    private static void startWeb() throws IOException {
        WebServer webServer = new WebServer(HTTP_PORT, BACKLOGS);

        HttpServer server = webServer.createServer();
        Routes.registerRoutes(server);
        server.setExecutor(null);

        server.start();

        System.out.println("Server is running");
    }

    private static void startSocket() {
        WebSocketServer webSocketServer = new SocketServer(SOCKET_PORT);
        webSocketServer.start();
    }

    private static void loadEnv() {
        SOCKET_PORT = Integer.parseInt(Env.get("SOCKET_PORT"));
        HTTP_PORT = Integer.parseInt(Env.get("SERVER_PORT"));
        BACKLOGS = Integer.parseInt(Env.get("SERVER_BACKLOGS"));
    }
}
