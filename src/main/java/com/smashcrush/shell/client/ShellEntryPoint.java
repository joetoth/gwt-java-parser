package com.smashcrush.shell.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class ShellEntryPoint implements EntryPoint {

  Logger log = Logger.getLogger("shell");

  ClientInjector injector = GWT.create(ClientInjector.class);

  @Override
  public void onModuleLoad() {
    injector.getApplication().start();
//
//    // setupKeyboardShortcuts();
    RootPanel.get("loading").setVisible(false);
  }
  
}
