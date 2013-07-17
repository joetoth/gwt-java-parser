package com.smashcrush.shell.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler interface for {@link OpenCommandBarEvent} events.
 */
public interface OpenCommandHandler extends EventHandler {

	/**
	 * Called when KeyPressEvent is fired.
	 * 
	 * @param event
	 *            the {@link OpenCommandBarEvent} that was fired
	 */
	void onKeySequence(OpenCommandBarEvent event);
}