package com.vini.socket.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mustachejava.Mustache;
import com.vini.game.board.Board;
import com.vini.game.interfaces.IGameMode;
import com.vini.socket.enums.MessageType;
import com.vini.web.lib.MustacheFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class RenderBoardHandler {

    private static final Mustache template;
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        template = MustacheFactory.getInstance().compile("board.mustache");
    }

    public static String render(MessageType type, IGameMode game) throws IOException {
        Board board = game.getBoard();
        byte[] buff;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                Writer writer = new OutputStreamWriter(out); ) {
            template.execute(writer, board.export()).flush();
            buff = out.toByteArray();
        }

        var page = new String(buff, StandardCharsets.UTF_8);
        var socketResponse = new SocketResponse(type, page);
        return mapper.writeValueAsString(socketResponse);
    }

    static class SocketResponse {
        public String type;
        public String body;

        public SocketResponse(MessageType type, String body) {
            this.type = type.name();
            this.body = body;
        }
    }
}
