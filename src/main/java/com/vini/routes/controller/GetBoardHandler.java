package com.vini.routes.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.sun.net.httpserver.HttpExchange;
import com.vini.shared.DefaultTemplateController;

public class GetBoardHandler extends DefaultTemplateController {

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Writer writer = new OutputStreamWriter(out);
  }
}
