package com.smashcrush.shell.client.view;

import java.util.logging.Logger;

import com.google.common.base.Preconditions;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.smashcrush.shell.shared.project.ProjectServiceAsync;

@Singleton
public class EditorView extends Composite {

  Logger log = Logger.getLogger("aaa");

  @UiTemplate("EditorView.ui.xml")
  public interface MyBinder extends UiBinder<Widget, EditorView> {
  }

  @UiField
  RichTextArea editor;

  private ProjectServiceAsync projectService;

  @Inject
  public EditorView(MyBinder binder, ProjectServiceAsync projectService) {
    this.projectService = Preconditions.checkNotNull(projectService);

    initWidget(binder.createAndBindUi(this));
  }

  public String getText() {
    return editor.getText();
  }

}