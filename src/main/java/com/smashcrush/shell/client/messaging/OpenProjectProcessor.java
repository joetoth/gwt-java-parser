package com.smashcrush.shell.client.messaging;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.smashcrush.shell.client.files.FileManager;
import com.smashcrush.shell.shared.Project;

@Singleton
public class OpenProjectProcessor {

  private FileManager fileManager;

  @Inject
  public OpenProjectProcessor(FileManager fileManager) {
	// TODO: Close all open windows
	this.fileManager = fileManager;
  }

  public void process(Project project) {
	fileManager.setFileNodes(project.getFileNodes());
  }

}
