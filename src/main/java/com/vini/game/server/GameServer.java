package com.vini.game.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.vini.game.Game;
import com.vini.game.GameManager;
import com.vini.game.enums.ColorEnum;
import com.vini.game.fen.Fen;

public class GameServer extends Thread {
  private final Socket socket;
  private final OutputStream out;
  private final BufferedReader in;
  private final GameManager gameManager;

  public GameServer(Socket socket) throws IOException {
    this.socket = socket;

    Charset charset = StandardCharsets.UTF_8;
    this.out = socket.getOutputStream();
    this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), charset));

    String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    ColorEnum turn = ColorEnum.WHITE;

    Game game = new Game(Fen.build(fen), turn);
    game.attachGameOverPieces();

    this.gameManager = new GameManager(game, socket);
  }

  @Override
  public void run() {
    while (this.socket.isConnected()) {
      String message;

      try {
        message = this.in.readLine();
        this.in.reset();

        boolean needRefresh = this.gameManager.action(message);

        if (needRefresh) {
          this.out.write("refresh".getBytes());
        }
      } catch (IOException err) {
        err.printStackTrace();
        continue;
      }
    }
  }
}
