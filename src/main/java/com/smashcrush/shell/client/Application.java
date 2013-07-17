package com.smashcrush.shell.client;

import com.google.common.base.Preconditions;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.inject.Inject;
import com.smashcrush.shell.client.place.Places;
import com.smashcrush.shell.client.view.EditorView;
import com.smashcrush.shell.client.view.StatusView;
import com.smashcrush.shell.client.view.navigation.NavigationView;

public class Application {

  PlaceController placeController;

  @Inject
  public Application(PlaceController placeController,
      ActivityManager activityManager) {
    this.placeController = Preconditions.checkNotNull(placeController);

    final SplitLayoutPanel p = new SplitLayoutPanel();
    RootLayoutPanel rp = RootLayoutPanel.get();
    rp.add(p);

    activityManager.setDisplay(new AcceptsOneWidget() {
      @Override
      public void setWidget(IsWidget w) {
        if (w instanceof NavigationView) {
          p.addWest(w, 150);
        } else if (w instanceof EditorView) {
          p.add(w);
        } else if (w instanceof StatusView) {
          p.addSouth(w, 200);
        }
      }
    });
  }

  public void start() {
    placeController.goTo(Places.STATUS_PLACE);
    placeController.goTo(Places.NAVIGATION_PLACE);
    placeController.goTo(Places.EDITOR_PLACE);
  }

}
