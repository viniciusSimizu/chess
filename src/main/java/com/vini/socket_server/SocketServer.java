package com.vini.socket_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Thread {
	@Override
	public void run() {
		int port;
		ServerSocket server;

		/*
		   try {
		   server = new ServerSocket(port);

		   while (true) {
		   Socket socket = server.accept();
		   new GameServer(socket).start();
		   }
		   } catch (IOException | NumberFormatException err) {
		   err.printStackTrace();
		   }
		   */
	}
}
