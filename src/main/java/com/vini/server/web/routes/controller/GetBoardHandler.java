package com.vini.server.web.routes.controller;

import com.sun.net.httpserver.HttpExchange;
import com.vini.shared.DefaultTemplateController;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class GetBoardHandler extends DefaultTemplateController {

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Writer writer = new OutputStreamWriter(out);
  }
}
