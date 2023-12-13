package com.vini.socket_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

import com.vini.game.Game;
import com.vini.game.GameManager;
import com.vini.game.fen.Fen;
import com.vini.game.mode.Mode;
import com.vini.game.mode.ModeRegistry;

public class GameServer extends Thread {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private GameManager gameManager;

	public GameServer(Socket socket) throws IOException {
		this.socket = socket;

		Charset charset = Charset.forName("UTF-8");
		this.out = new PrintWriter(socket.getOutputStream(), true, charset);
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), charset));

		Mode mode = ModeRegistry.classic;

		Game game = new Game(Fen.build(mode.fen), mode.turn);
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
			} catch (IOException err) {
				err.printStackTrace();
				continue;
			}

			boolean needRefresh = this.gameManager.action(message);

			if (needRefresh) {
				this.out.println("refresh");
			}
		}
	}
}
