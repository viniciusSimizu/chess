package com.vini.socket.lib;

import com.github.mustachejava.Mustache;
import com.vini.game.interfaces.IGame;
import com.vini.socket.enums.MessageTypeEnum;
import com.vini.web.lib.MustacheFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class RenderBoard {

    private static final Mustache template;

    static {
        template = MustacheFactory.getInstance().compile("board.mustache");
    }

    public static String render(MessageTypeEnum type, IGame game) throws IOException {
        byte[] buff;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                Writer writer = new OutputStreamWriter(out); ) {
            template.execute(writer, game.export()).flush();
            buff = out.toByteArray();
        }

        var page = new String(buff, StandardCharsets.UTF_8);
        var socketResponse = new SocketResponse(type, page);
        return JsonHandler.writeValueAsString(socketResponse);
    }

    static class SocketResponse {
        public String type;
        public String body;

        public SocketResponse(MessageTypeEnum type, String body) {
            this.type = type.name();
            this.body = body;
        }
    }
}
