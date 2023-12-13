package com.vini.config;

public class WebServerConfig {

	private static WebServerConfig instance;

	public final int PORT;
	public final int BACKLOGS;

	protected WebServerConfig() {
		this.PORT = Integer.parseInt(System.getenv("SERVER_PORT"));
		this.BACKLOGS = Integer.parseInt(System.getenv("SERVER_BACKLOGS"));
	}

	public static WebServerConfig getInstance() {
		if (WebServerConfig.instance == null) {
			WebServerConfig.instance = new WebServerConfig();
		}
		return WebServerConfig.instance;
	}
}
