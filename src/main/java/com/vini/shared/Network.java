package com.vini.shared;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Network {
  private static Network INSTANCE;

  public final int SOCKET_PORT;
  public final InetAddress ADDRESS;

  private Network(InetAddress ADDRESS, int PORT) {
    this.SOCKET_PORT = PORT;
    this.ADDRESS = ADDRESS;
  }

  public static Network getInstance() throws UnknownHostException {
    if (Network.INSTANCE == null) {
      InetAddress address = InetAddress.getLocalHost();
      int port = Integer.parseInt(Env.get("SOCKET_PORT"));
      Network.INSTANCE = new Network(address, port);
    }
    return Network.INSTANCE;
  }
}
