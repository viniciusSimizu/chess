package com.vini.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.vini.game.server.GameServer;
import com.vini.shared.Env;

public class SocketServer extends Thread {
  private final int PORT = Integer.parseInt(Env.get("SOCKET_PORT"));
  private final ServerSocket server;

  public SocketServer() throws IOException {
    this.server = new ServerSocket(this.PORT);
  }

  @Override
  public void run() {
    try {
      while (true) {
        Socket socket = this.server.accept();
        new GameServer(socket).start();
      }
    } catch (IOException err) {
      err.printStackTrace();
    }
  }
}
