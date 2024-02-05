package com.vini.web.lib;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mustachejava.DefaultMustacheFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public abstract class DefaultController implements HttpHandler {

    protected <T> T body(HttpExchange ex, Class<T> objectClass) {
        var buff = new byte[1024];
        var objMapper = new ObjectMapper();

        try {
            ex.getRequestBody().read(buff);
        } catch (IOException e) {
            e.printStackTrace();
        }

        var data = new String(buff, StandardCharsets.UTF_8);

        try {
            return objMapper.readValue(data, objectClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void response(HttpExchange ex, byte[] res) throws IOException {
        try (OutputStream out = ex.getResponseBody(); ) {
            out.write(res);
        }
    }

    protected DefaultMustacheFactory getMustacheFactory() {
        return MustacheFactory.getInstance();
    }
}
