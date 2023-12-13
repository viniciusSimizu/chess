package com.vini;

import java.io.IOException;

import com.vini.web_server.WebServer;

public class App {
    public static void main( String[] args ) throws IOException {
        System.out.println("Hello World!");
		new WebServer();
    }
}
