package com.vini.server.socket;

import com.vini.game.server.GameServer;
import com.vini.server.database.OpenGameServers;
import com.vini.shared.Network;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class SocketServer extends Thread {
  private final OpenGameServers gameServers = OpenGameServers.getInstance();
  private final ServerSocket serverSocket;

  public SocketServer() throws IOException, UnknownHostException {
    Network net = Network.getInstance();
    this.serverSocket = new ServerSocket(net.PORT);
  }

  @Override
  public void run() {
    try {
      Socket playerAwaitingMatch = null;

      while (true) {
        Socket playerConnected = this.serverSocket.accept();

        if (playerAwaitingMatch == null) {
          playerAwaitingMatch = playerConnected;
          continue;
        }

        GameServer gameServer = new GameServer();
        this.gameServers.registerServer(gameServer);

        for (Socket playerConnection :
             Arrays.asList(playerAwaitingMatch, playerConnected)) {
          PlayerData playerData = new PlayerData(playerConnection);
          gameServer.attachPlayer(playerData);
        };

        gameServer.run();
      }
    } catch (IOException err) {
      err.printStackTrace();
    }
  }
}
