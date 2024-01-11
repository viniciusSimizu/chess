package com.vini.server.web.routes;

import com.sun.net.httpserver.HttpServer;
import com.vini.server.web.routes.controller.GetIndexHandler;
import com.vini.server.web.routes.controller.PublicFileHandler;

public class Routes {
  public static void registerRoutes(HttpServer httpServer) {
    httpServer.createContext("/assets", new PublicFileHandler());

    httpServer.createContext("/", new GetIndexHandler());
  }
}
