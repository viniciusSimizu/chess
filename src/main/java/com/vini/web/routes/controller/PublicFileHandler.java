package com.vini.web.routes.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PublicFileHandler implements HttpHandler {
    private static final String RESOURCES_PATH = "/src/main/resources/static";
    public static final String WORKSPACE_DIRECTORY = System.getProperty("user.dir");

    public static byte[] readFile(String context) throws IOException {
        byte[] body;

        StringBuilder path = new StringBuilder();
        path.append(PublicFileHandler.WORKSPACE_DIRECTORY);
        path.append(PublicFileHandler.RESOURCES_PATH);
        path.append(context);

        FileReader file = new FileReader(path.toString());
        StringBuilder content = new StringBuilder();

        for (int nextChar = file.read(); nextChar != -1; nextChar = file.read()) {
            content.append((char) nextChar);
        }
        file.close();

        body = content.toString().getBytes();
        return body;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream res = exchange.getResponseBody();
        byte[] body;

        Pattern ignore = Pattern.compile("^\\/assets/static(.*)");
        Matcher match = ignore.matcher(exchange.getRequestURI().getPath());

        StringBuilder path = new StringBuilder();
        path.append(PublicFileHandler.WORKSPACE_DIRECTORY);
        path.append(PublicFileHandler.RESOURCES_PATH);

        FileReader file;

        try {
            if (!match.find()) {
                throw new FileNotFoundException();
            }

            path.append(match.group(1));
            file = new FileReader(path.toString());
        } catch (FileNotFoundException err) {
            body = ("File Not Found").getBytes();

            exchange.sendResponseHeaders(404, body.length);
            res.write(body);
            res.close();
            return;
        }

        StringBuilder content = new StringBuilder();

        for (int nextChar = file.read(); nextChar != -1; nextChar = file.read()) {
            content.append((char) nextChar);
        }

        body = content.toString().getBytes();

        exchange.sendResponseHeaders(200, body.length);
        res.write(body);
        res.close();
        file.close();
    }
}
