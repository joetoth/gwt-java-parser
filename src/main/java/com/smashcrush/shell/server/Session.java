package com.smashcrush.shell.server;

import java.io.Serializable;

public class Session implements Serializable {
  String projectPath;

  public String getProjectPath() {
    return projectPath;
  }

  public void setProjectPath(String projectPath) {
    this.projectPath = projectPath;
  }
}
