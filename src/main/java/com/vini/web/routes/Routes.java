package com.vini.web.routes;

import com.sun.net.httpserver.HttpServer;
import com.vini.web.routes.controller.GetBoardHandler;
import com.vini.web.routes.controller.GetIndexHandler;
import com.vini.web.routes.controller.PublicFileHandler;

public class Routes {
    public static void registerRoutes(HttpServer httpServer) {
        httpServer.createContext("/assets", new PublicFileHandler());

        httpServer.createContext("/", new GetIndexHandler());
        httpServer.createContext("/board", new GetBoardHandler());
    }
}