package com.vini.web;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.vini.shared.Env;

public class WebServer {

  private final static int PORT = Integer.parseInt(Env.get("SERVER_PORT"));

  private final static int BACKLOGS = Integer.parseInt(Env.get("SERVER_BACKLOGS"));

  public static HttpServer createServer() throws IOException {
    InetSocketAddress socketAddress = new InetSocketAddress(WebServer.PORT);
    return HttpServer.create(socketAddress, WebServer.BACKLOGS);
  }
}
