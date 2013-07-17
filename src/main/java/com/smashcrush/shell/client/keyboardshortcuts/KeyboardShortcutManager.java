package com.smashcrush.shell.client.keyboardshortcuts;

import java.util.List;
import java.util.logging.Logger;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.smashcrush.shell.client.event.OpenCommandBarEvent;

@Singleton
public class KeyboardShortcutManager {

  Logger log = Logger.getLogger(KeyboardShortcutManager.class.getName());

  List<NativeEvent> sequence = Lists.newArrayList();

  private final EventBus eventBus;

  @Inject
  public KeyboardShortcutManager(EventBus eventBus) {
    this.eventBus = Preconditions.checkNotNull(eventBus);
  }

  public void processKey(NativeEvent key) {
    log.fine(key.getCtrlKey() + " --- " + key.getCharCode() + " - " + key.getKeyCode());
    sequence.add(key);
//    key.preventDefault();
    
    if (key.getCtrlKey() && key.getKeyCode() == 68) {
log.fine("yup!!!");
      key.preventDefault();
      eventBus.fireEvent(new OpenCommandBarEvent());
    }
  }

}
