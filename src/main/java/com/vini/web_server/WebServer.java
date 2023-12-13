package com.vini.web_server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.vini.config.WebServerConfig;

public class WebServer {

	private HttpServer server;
	private WebServerConfig config = WebServerConfig.getInstance();

	public WebServer() throws IOException {
		System.out.println(this.config.PORT);
		System.out.println(this.config.BACKLOGS);
		//InetSocketAddress socketAddress = new InetSocketAddress(this.config.PORT);
		//this.server = HttpServer.create(socketAddress, this.config.BACKLOGS);
	}
}
