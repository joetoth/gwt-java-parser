package com.smashcrush.shell.client;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.smashcrush.shell.client.place.Places;

public class ShellModule extends AbstractGinModule {

  Logger log = Logger.getLogger("shellmodule");
  
  @WithTokenizers({ Places.Tokenizer.class })
  public interface ShellPlaceHistoryMapper extends PlaceHistoryMapper {
  }

  protected void configure() {

    // EventBus eventBus = new SimpleEventBus();
    // PlaceController placeController = new PlaceController(eventBus);

    bind(EventBus.class).to(SimpleEventBus.class).asEagerSingleton();
    // bind(PlaceController.class).

    // bind(MessageDispatcher.class).to(MessageDispatcher.class);
    // bind(CommandView.class).to(CommandViewImpl.class);
    // bind(ShellView.class).to(ShellViewImpl.class);

    // HelloView helloView = new HelloViewImpl();
    // private final GoodbyeView goodbyeView = new GoodbyeViewImpl();

    // bind(MyWidgetMainPanel.class).in(Singleton.class);
    // bind(MyRemoteService.class).toProvider(MyRemoteServiceProvider.class);
  }

  @Provides
  @Singleton
  PlaceController getPlaceController(EventBus eventBus) {
    return new PlaceController(eventBus);
  }

  @Provides
  @Singleton
  ActivityManager getActivityManager(ShellActivityMapper mapper,
      EventBus eventBus) {
    return new ActivityManager(mapper, eventBus);
  }

  /**
   * Sets up the default place to start.
   */
  @Provides
  @Singleton
  PlaceHistoryHandler getPlaceHistoryHandler(
      ShellPlaceHistoryMapper historyMapper, PlaceController placeController,
      EventBus eventBus) {
    log.fine("getting history");
    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
    historyHandler.register(placeController, eventBus, Places.NOWHERE);
    return historyHandler;
  }

}