package com.smashcrush.shell.client.activity;

import com.google.common.base.Preconditions;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.IsWidget;

public abstract class BaseActivity<V extends IsWidget> implements Activity {

	private final PlaceController placeController;

	protected BaseActivity(final V view, final PlaceController placeController) {
		this.placeController = Preconditions.checkNotNull(placeController);
	}

	/**
	 * Navigates to newPlace.
	 */
	public void goTo(Place newPlace) {
		placeController.goTo(newPlace);
	}

	protected Place getWhere() {
		return (Place) placeController.getWhere();
	}
}