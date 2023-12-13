package com.vini.routes;

import com.sun.net.httpserver.HttpServer;
import com.vini.routes.controller.GetIndexHandler;

public class Routes {
  public static void registerRoutes(HttpServer httpServer) {
    httpServer.createContext("/", new GetIndexHandler());
  }
}
