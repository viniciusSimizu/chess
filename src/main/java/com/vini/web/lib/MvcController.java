package com.vini.web.lib;

import com.github.mustachejava.DefaultMustacheFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public abstract class MvcController implements HttpHandler {
    protected final DefaultMustacheFactory mustacheFactory;

    protected MvcController() {
        this.mustacheFactory = MustacheFactory.getInstance();
    }

    protected void response(HttpExchange ex, byte[] res) throws IOException {
        try (OutputStream out = ex.getResponseBody(); ) {
            out.write(res);
        }
    }
}
