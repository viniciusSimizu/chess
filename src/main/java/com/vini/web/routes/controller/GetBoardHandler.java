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

            TableRepresentation table = GameModel.INSTANCE.export();
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

        public List<Row> rows = new ArrayList<>();

        public ViewData(TableRepresentation table) {
            for (int i = 0; i < table.rows; i++) {
                var rowSquares =
                        table.representation.subList(table.columns * i, table.columns * (i + 1));
                this.rows.add(new Row(rowSquares));
            }
        }

        class Row {

            public List<Square> squares = new ArrayList<>();

            public Row(List<String> squares) {
                for (String identifiers : squares) {
                    this.squares.add(new Square(identifiers));
                }
            }
        }

        class Square {

            public boolean isPiece = false;
            public String identifiers;

            public Square(String identifiers) {
                this.identifiers = identifiers;
								if (identifiers != null && !identifiers.isEmpty()) {
									this.isPiece = true;
								}
            }
        }
    }
}
