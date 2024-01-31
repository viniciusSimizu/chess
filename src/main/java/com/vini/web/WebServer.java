package com.vini.web;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {

    private int port, backlogs;

    public WebServer(int port, int backlogs) {
        this.port = port;
        this.backlogs = backlogs;
    }

    public HttpServer createServer() throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(this.port);
        return HttpServer.create(socketAddress, this.backlogs);
    }
}
