package io.github.matsior.summerframework.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class WebServer implements Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebServer.class);

  private final int port;

  public WebServer(int port) {
    this.port = port;
  }

  @Override
  public void run() {
    initialize();
  }

  private void initialize() {
    try {
      LOGGER.info("Server starting...");
      ServerSocket serverSocket = new ServerSocket(port);
      Socket socket = serverSocket.accept();

      InputStream inputStream = socket.getInputStream();
      OutputStream outputStream = socket.getOutputStream();


    } catch (IOException e) {
      // TODO
    }
  }

}
