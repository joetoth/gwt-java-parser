package com.smashcrush.shell.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketFactory;

import com.google.inject.Singleton;

@Singleton
class WebSocketSessionServlet extends HttpServlet {

  private WebSocketFactory _wsFactory;

  @Override
  public void init() throws ServletException {
	// Create and configure WS factory
	_wsFactory = new WebSocketFactory(new WebSocketFactory.Acceptor() {
	  public boolean checkOrigin(HttpServletRequest request, String origin) {
		// Allow all origins
		return true;
	  }

	  public WebSocket doWebSocketConnect(HttpServletRequest request,
		  String protocol) {
		System.out.println("protocal: " + protocol);
		if ("chat".equals(protocol))
		  return new ChatWebSocket();
		return null;
	  }
	});
	_wsFactory.setBufferSize(4096);
	_wsFactory.setMaxIdleTime(60000);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
	System.out.println("?????connected!!!");
	if (_wsFactory.acceptWebSocket(request, response)) {
	  System.out.println("connected!!!");
	  return;
	}
	response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,
	    "Websocket only");
  }

  private class ChatWebSocket implements WebSocket.OnTextMessage {
	Connection _connection;

	public void onOpen(Connection connection) {
	  _connection = connection;
	  System.out.println("OPEN!");
	}

	public void onClose(int closeCode, String message) {
	  System.out.println("CLOSE!");
	}

	public void onMessage(String data) {
	  System.out.println("Message: " + data);
	}
  }
}