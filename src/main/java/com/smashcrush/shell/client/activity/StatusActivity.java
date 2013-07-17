package com.smashcrush.shell.client.activity;

import java.util.logging.Logger;

import com.google.common.base.Preconditions;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.inject.Inject;
import com.smashcrush.shell.client.event.OpenCommandBarEvent;
import com.smashcrush.shell.client.event.OpenCommandHandler;
import com.smashcrush.shell.client.keyboardshortcuts.KeyboardShortcutManager;
import com.smashcrush.shell.client.view.CommandView;
import com.smashcrush.shell.client.view.StatusView;

import elemental.events.EventListener;
import elemental.html.WebSocket;
import elemental.js.JsBrowser;

public class StatusActivity extends BaseActivity<StatusView> {

  Logger log = Logger.getLogger("ee");

  StatusView statusView;
  CommandView commandView;
  KeyboardShortcutManager keyboardShortcutManager;

  @Inject
  public StatusActivity(StatusView statusView, CommandView commandView,
      PlaceController placeController) {
    super(statusView, placeController);
    this.statusView = Preconditions.checkNotNull(statusView);
    this.commandView = Preconditions.checkNotNull(commandView);
//    this.keyboardShortcutManager = Preconditions
//        .checkNotNull(keyboardShortcutManager);
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(statusView);

//    log.info("started shell" + panel);
//    // panel.setWidget(shellView);
//
//    setupKeyboardShortcuts();
//    // connectWebSocket();
//
//    eventBus.addHandler(OpenCommandBarEvent.getType(),
//        new OpenCommandHandler() {
//          @Override
//          public void onKeySequence(OpenCommandBarEvent event) {
//            openCommandBar();
//          }
//        });
  }

  private void connectWebSocket() {
    WebSocket socket = JsBrowser.getWindow().newWebSocket(
        "ws://localhost:8080/socket");
    socket.setOnmessage(new EventListener() {

      @Override
      public void handleEvent(elemental.events.Event evt) {

      }
    });
    socket.send("cat");
  }

  private void setupKeyboardShortcuts() {
    // Define an inner class to handle the event
    Event.addNativePreviewHandler(new NativePreviewHandler() {
      public void onPreviewNativeEvent(NativePreviewEvent preview) {
        NativeEvent event = preview.getNativeEvent();

        // System.out.println(event.getType());

        int keycode = event.getKeyCode();
        boolean ctrl = event.getCtrlKey();
        boolean shift = event.getShiftKey();
        boolean alt = event.getAltKey();
        boolean meta = event.getMetaKey();
        // System.out.println("-" + event.getType() + "-");
        if (event.getType().equalsIgnoreCase("keypress") || ctrl || shift
            || alt || meta) {

          keyboardShortcutManager.processKey(event);
          // Tell the event handler to continue processing this event.
          return;
        }

        // System.out.println("didn't process" + event.getType());
        // handleKeycode(keycode);

        // Tell the event handler that this event has been consumed
        // preview.consume();

      }
    });
  }

  private void openCommandBar() {
    DialogBox d = new DialogBox(true);
    d.center();
    d.add(commandView);
    d.show();
  }

  @Override
  public String mayStop() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void onCancel() {
    // TODO Auto-generated method stub

  }

  @Override
  public void onStop() {
    // TODO Auto-generated method stub

  }
}
