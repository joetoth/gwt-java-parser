package com.smashcrush.shell.shared.project;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smashcrush.shell.shared.Project;

public interface ProjectServiceAsync {
  public void openProject(String path, AsyncCallback<Project> callback);
}
