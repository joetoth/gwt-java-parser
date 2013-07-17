package com.smashcrush.shell.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.smashcrush.shell.client.messaging.MessageDispatcher;

@GinModules({ ShellModule.class })
public interface ClientInjector extends Ginjector {

	/** Manages activities. */
	ActivityManager getActivityManager();

	/** Handles browser history events. */
	PlaceHistoryHandler getPlaceHistoryHandler();
	
	MessageDispatcher getMessageDispatcher();
	
	PlaceController getPlaceController();
	
	Application getApplication();
}
