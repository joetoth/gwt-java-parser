package com.smashcrush.shell.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.smashcrush.shell.server.service.ProjectServiceImpl;

public class SmashCrushModule extends GuiceServletContextListener {

  private class SmashCrushServletModule extends ServletModule {
	@Override
	protected void configureServlets() {
	  serve("/socket").with(WebSocketSessionServlet.class);
	  serve("/com.smashcrush.shell.Module/services/project").with(ProjectServiceImpl.class);
	}
  }

  @Override
  protected Injector getInjector() {
	return Guice.createInjector(new SmashCrushServletModule());
  }
}