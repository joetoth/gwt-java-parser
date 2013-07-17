package com.smashcrush.shell.client.view;

import java.util.logging.Logger;

import com.google.common.base.Preconditions;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.smashcrush.shell.client.messaging.OpenProjectProcessor;
import com.smashcrush.shell.shared.Project;
import com.smashcrush.shell.shared.project.ProjectServiceAsync;

@Singleton
public class CommandView extends Composite {

  Logger log = Logger.getLogger("aaa");
  
  @UiTemplate("CommandView.ui.xml")
  public interface MyBinder extends UiBinder<Widget, CommandView> {
  }

  @UiField
  TextBox commandTextBox;

  private ProjectServiceAsync projectService;
  private OpenProjectProcessor openProjectProcessor;

  @Inject
  public CommandView(MyBinder binder, ProjectServiceAsync projectService,
	  OpenProjectProcessor openProjectProcessor) {
	this.projectService = Preconditions.checkNotNull(projectService);
	this.openProjectProcessor = Preconditions
	    .checkNotNull(openProjectProcessor);
	initWidget(binder.createAndBindUi(this));
	commandTextBox.getElement().setAttribute("placeholder", "command");
  }

  @Override
  protected void onAttach() {
	commandTextBox.setFocus(true);
	super.onAttach();
  }

  @UiHandler("commandTextBox")
  protected void onEnter(KeyPressEvent event) {
	log.fine("sys " + event.getUnicodeCharCode());
	if (event.getUnicodeCharCode() == 13) {
	  String path = commandTextBox.getText();
	  log.fine("calllling " + path);
	  projectService.openProject(path, new AsyncCallback<Project>() {

		@Override
		public void onSuccess(Project project) {
		  log.fine("abouttoprocess" + project);
		  openProjectProcessor.process(project);
		}

		@Override
		public void onFailure(Throwable caught) {
		  // TODO Auto-generated method stub

		}
	  });
	}
  }

}