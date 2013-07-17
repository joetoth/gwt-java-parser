package com.smashcrush.shell.client;

import java.util.logging.Logger;

import com.google.common.collect.ImmutableMap;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.smashcrush.shell.client.activity.BaseActivity;
import com.smashcrush.shell.client.activity.CommandActivity;
import com.smashcrush.shell.client.activity.EditorActivity;
import com.smashcrush.shell.client.activity.NavigationActivity;
import com.smashcrush.shell.client.activity.StatusActivity;
import com.smashcrush.shell.client.place.Places;

/**
 * Maps places to activities, effectively controls the navigation in the app and
 * is responsible for passing around the configuration map to persist state.
 * 
 * @author joetoth@gmail.com (Joe Toth)
 */
public class ShellActivityMapper implements ActivityMapper {

  Logger log = Logger.getLogger(ShellActivityMapper.class.getName());

  private final ImmutableMap<Places, Provider<? extends BaseActivity<? extends IsWidget>>> activities;

  @Inject
  public ShellActivityMapper(Provider<CommandActivity> commandActivity,
      Provider<EditorActivity> editorActivity,
      Provider<NavigationActivity> navigationActivity,
      Provider<StatusActivity> statusActivity) {
    activities = ImmutableMap
        .<Places, Provider<? extends BaseActivity<? extends IsWidget>>> builder()
        .put(Places.EDITOR_PLACE, editorActivity)
        .put(Places.NAVIGATION_PLACE, navigationActivity)
        .put(Places.STATUS_PLACE, statusActivity).build();
  }

  @Override
  public Activity getActivity(Place place) {
    log.fine("get act for " + place);
    if (place instanceof Places) {
      
      Provider m = activities.get(place);
      log.fine("its aninsance" + m);
      final BaseActivity<? extends IsWidget> activity = activities.get(place)
          .get();
      log.fine("got act" + activity);
      return activity;
    }
    throw new IllegalStateException("Unsupported place: " + place);
  }
}