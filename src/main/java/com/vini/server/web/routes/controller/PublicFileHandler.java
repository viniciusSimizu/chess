package com.vini.server.web.routes.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;

public class PublicFileHandler implements HttpHandler {
  private String WORKSPACE_DIRECTORY = System.getProperty("user.dir");

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    OutputStream res = exchange.getResponseBody();
    byte[] body;

    String path = this.WORKSPACE_DIRECTORY + exchange.getRequestURI().getPath();
    FileReader file;

    try {
      file = new FileReader(path);
    } catch (FileNotFoundException err) {
      body = ("File Not Found").getBytes();

      exchange.sendResponseHeaders(404, body.length);
      res.write(body);
      res.close();
      return;
    }

    StringBuilder content = new StringBuilder();

    for (int nextChar = file.read(); nextChar != -1; nextChar = file.read()) {
      content.append((char) nextChar);
    }

    body = content.toString().getBytes();

    exchange.sendResponseHeaders(200, body.length);
    res.write(body);
    res.close();
    file.close();
  }
}
