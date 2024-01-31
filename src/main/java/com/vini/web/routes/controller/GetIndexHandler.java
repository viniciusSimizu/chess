package com.vini.web.routes.controller;

import com.github.mustachejava.Mustache;
import com.sun.net.httpserver.HttpExchange;
import com.vini.web.lib.DefaultTemplateController;

import java.io.*;

public class GetIndexHandler extends DefaultTemplateController {

    private final Mustache template;

    public GetIndexHandler() {
        this.template = this.mustacheFactory.compile("index.mustache");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(out);
        Variables variables = new Variables("pare");

        this.template.execute(writer, variables).flush();
        writer.close();

        byte[] page = out.toByteArray();

        exchange.sendResponseHeaders(200, page.length);
        OutputStream res = exchange.getResponseBody();
        res.write(page);
        res.close();
    }

    class Variables {
        public String pare;

        public Variables(String pare) {
            this.pare = pare;
        }
    }
}
