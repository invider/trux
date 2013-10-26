package com.decaflabs.trux.rest;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * Embedded HTTP Server (based on Jetty)
 */
public class TruxServer implements Runnable {

	public TruxServer() {
		Thread thread = new Thread(this, "trux-http");
		thread.start();
	}

	@Override
	public void run() {
		try {
			Server server = new Server(8080);

			ResourceHandler resource_handler = new ResourceHandler();
			resource_handler.setDirectoriesListed(true);
			resource_handler.setWelcomeFiles(new String[] { "index.html" });

			resource_handler.setResourceBase("./www");

			HandlerList handlers = new HandlerList();
			handlers.setHandlers(new Handler[] { resource_handler,
					new HelloHandler() });
			server.setHandler(handlers);

			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
