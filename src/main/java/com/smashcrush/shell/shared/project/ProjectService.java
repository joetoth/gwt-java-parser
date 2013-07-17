package com.smashcrush.shell.shared.project;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.smashcrush.shell.shared.Project;

@RemoteServiceRelativePath("services/project")
public interface ProjectService extends RemoteService {
  public Project openProject(String path);
}
