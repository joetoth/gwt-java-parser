package com.smashcrush.shell.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.smashcrush.shell.client.view.navigation.NavigationView;

public class NavigationActivity extends BaseActivity<NavigationView> {

  NavigationView view;

  @Inject
  public NavigationActivity(NavigationView view, PlaceController placeController) {
    super(view, placeController);
    this.view = view;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(view);
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
