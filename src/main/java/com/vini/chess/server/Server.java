package com.vini.chess.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;

import com.vini.chess.config.ServerConfig;

public class Server extends Thread {
	@Autowired
	private ServerConfig config;

	@Override
	public void run() {
		int port;
		ServerSocket server;

		try {
			System.out.println(this.config.port());
			port = this.config.port();
			server = new ServerSocket(port);
			System.out.println("testetestes");

			while (true) {
				Socket socket = server.accept();
				new GameServer(socket).start();
			}
		} catch (IOException | NumberFormatException err) {
			err.printStackTrace();
		}
	}
}
