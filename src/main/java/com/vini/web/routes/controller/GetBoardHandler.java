package com.vini.web.routes.controller;

import com.github.mustachejava.Mustache;
import com.sun.net.httpserver.HttpExchange;
import com.vini.socket.lib.TableRepresentation;
import com.vini.socket.models.GameModel;
import com.vini.web.lib.MvcController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class GetBoardHandler extends MvcController {

    private final Mustache template;

    public GetBoardHandler() {
        this.template = this.mustacheFactory.compile("board.mustache");
    }

    @Override
    public void handle(HttpExchange exchange) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                Writer writer = new OutputStreamWriter(out); ) {

            TableRepresentation representation = GameModel.INSTANCE.export();

            this.template.execute(writer, representation).flush();
            byte[] page = out.toByteArray();

            exchange.sendResponseHeaders(200, page.length);
            this.response(exchange, page);
        } catch (IOException e) {
            System.out.println("board error");
        }
    }
}
