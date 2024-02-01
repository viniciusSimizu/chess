package com.vini.web.routes.controller;

import com.github.mustachejava.Mustache;
import com.sun.net.httpserver.HttpExchange;
import com.vini.socket.models.GameModel;
import com.vini.web.lib.MvcController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GetBoardHandler extends MvcController {

    private final Mustache template;

    public GetBoardHandler() {
        this.template = this.mustacheFactory.compile("board.mustache");
    }

    @Override
    public void handle(HttpExchange exchange) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                Writer writer = new OutputStreamWriter(out); ) {

            List<List<String>> table = GameModel.INSTANCE.export();
            ViewData data = new ViewData(table);

            this.template.execute(writer, data).flush();
            byte[] page = out.toByteArray();

            exchange.sendResponseHeaders(200, page.length);
            this.response(exchange, page);
        } catch (IOException e) {
            System.out.println("board error");
        }
    }

    class ViewData {
        public List<Row> table = new ArrayList<>();

        public ViewData(List<List<String>> table) {
            for (List<String> row : table) {
                Row rowData = new Row(row);
                this.table.add(rowData);
            }
        }

        class Row {
            public List<Square> row = new ArrayList<>();

            public Row(List<String> row) {
                for (String identifiers : row) {
                    Square squareData = new Square(identifiers);
                    this.row.add(squareData);
                }
            }
        }

        class Square {
            public String identifiers;

            public Square(String identifiers) {
                this.identifiers = identifiers;
            }
        }
    }
}
