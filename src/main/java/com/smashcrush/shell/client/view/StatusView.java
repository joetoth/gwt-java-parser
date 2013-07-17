package com.smashcrush.shell.client.view;

import java.util.logging.Logger;

import com.google.common.base.Preconditions;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.smashcrush.shell.client.messaging.OpenProjectProcessor;
import com.smashcrush.shell.shared.project.ProjectServiceAsync;

@Singleton
public class StatusView extends Composite {

  Logger log = Logger.getLogger("aaa");
  
  @UiTemplate("StatusView.ui.xml")
  public interface MyBinder extends UiBinder<Widget, StatusView> {
  }

  private ProjectServiceAsync projectService;

  @Inject
  public StatusView(MyBinder binder, ProjectServiceAsync projectService,
	  OpenProjectProcessor openProjectProcessor) {
	this.projectService = Preconditions.checkNotNull(projectService);

	initWidget(binder.createAndBindUi(this));
  }

}