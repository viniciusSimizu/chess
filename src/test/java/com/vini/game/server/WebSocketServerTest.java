package com.vini.game.server;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vini.shared.Env;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WebSocketServerTest {
  /*
private ExecutorService executorService = Executors.newSingleThreadExecutor();
private WebSocketServer socketServer;
private Future<?> execution;

private InetAddress host;
private int port;

public SocketServerTest() throws UnknownHostException {
this.host = InetAddress.getLocalHost();
this.port = Integer.parseInt(Env.get("SOCKET_PORT"));
}

@BeforeEach
public void setup() throws UnknownHostException, IOException {
this.socketServer = new WebSocketServer();
this.execution = executorService.submit(socketServer);
}

@AfterEach
public void tearDown() throws InterruptedException {
this.execution.cancel(true);

while (!this.execution.isCancelled()) {
Thread.sleep(1000);
}
}

@Test
public void ClientCanConnect_Test() {
try (Socket client = new Socket(this.host, this.port)) {
assertTrue(client.isConnected());
} catch (IOException err) {
err.printStackTrace();
assert false;
}
}
  */
}
