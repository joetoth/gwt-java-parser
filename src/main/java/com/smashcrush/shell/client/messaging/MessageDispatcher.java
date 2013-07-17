package com.smashcrush.shell.client.messaging;

import com.google.inject.Inject;
import com.smashcrush.shell.shared.SmashCrushEvents;

import elemental.events.Event;
import elemental.json.JsonObject;

public class MessageDispatcher {

  @Inject
  public MessageDispatcher() {

  }

  public void dispatch(Event event) {
	Integer eventId = null;
	JsonObject object = null;

	switch (eventId) {
	case SmashCrushEvents.PROJECT_OPENED:
//	  projectOpened().process(object);
	  break;

	default:
	  break;
	}

  }


}
