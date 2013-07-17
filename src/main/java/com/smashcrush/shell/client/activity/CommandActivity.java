package com.smashcrush.shell.client.activity;

import com.google.common.base.Preconditions;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.smashcrush.shell.client.view.CommandView;

public class CommandActivity extends BaseActivity<CommandView> {

	CommandView commandView;

	@Inject
	public CommandActivity(CommandView commandView,
			PlaceController placeController) {
		super(commandView, placeController);
		this.commandView = Preconditions.checkNotNull(commandView);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(commandView);
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
