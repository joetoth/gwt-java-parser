package com.smashcrush.shell.client;

import com.google.inject.Inject;

import elemental.events.Event;
import elemental.events.EventListener;
import elemental.html.WebSocket;
import elemental.js.JsBrowser;

public class WebSocketEventBus {

  WebSocket socket;

  @Inject
  public WebSocketEventBus() {
	socket = JsBrowser.getWindow().newWebSocket("ws://localhost:8080/socket");
	socket.setOnmessage(new EventListener() {

	  @Override
	  public void handleEvent(Event event) {
		processMessage(event);
	  }
	});
  }

  private void processMessage(Event event) {
	System.out.println("event: " + event);
  }

}
