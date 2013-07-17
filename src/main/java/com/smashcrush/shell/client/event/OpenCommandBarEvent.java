package com.smashcrush.shell.client.event;

import com.google.gwt.event.dom.client.DomEvent;
import com.google.web.bindery.event.shared.Event;

/**
 * Represents a native key press event.
 */
public class OpenCommandBarEvent extends Event<OpenCommandHandler> {

	/**
	 * Event type for key press events. Represents the meta-data associated with
	 * this event.
	 */
	private static final Type<OpenCommandHandler> TYPE = new Type<OpenCommandHandler>();

	/**
	 * Gets the event type associated with key press events.
	 * 
	 * @return the handler type
	 */
	public static Type<OpenCommandHandler> getType() {
		return TYPE;
	}

	/**
	 * Protected constructor, use
	 * {@link DomEvent#fireNativeEvent(com.google.gwt.dom.client.NativeEvent, com.google.gwt.event.shared.HasHandlers)}
	 * to fire key press events.
	 */
	public OpenCommandBarEvent() {
	}

	@Override
	public final Type<OpenCommandHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toDebugString() {
		return super.toDebugString() + "[" +  "]";
	}

	@Override
	protected void dispatch(OpenCommandHandler handler) {
		handler.onKeySequence(this);
	}
}
