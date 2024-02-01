package com.vini.web.routes.controller;

import com.github.mustachejava.Mustache;
import com.sun.net.httpserver.HttpExchange;
import com.vini.web.lib.MvcController;

import java.io.*;

public class GetIndexHandler extends MvcController {

    private final Mustache template;

    public GetIndexHandler() {
        this.template = this.mustacheFactory.compile("index.mustache");
    }

    @Override
    public void handle(HttpExchange exchange) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                Writer writer = new OutputStreamWriter(out); ) {

            this.template.execute(writer, new Object()).flush();
            byte[] page = out.toByteArray();

            exchange.sendResponseHeaders(200, page.length);
            this.response(exchange, page);
        } catch (IOException e) {
            System.out.println("index error");
        }
    }
}
