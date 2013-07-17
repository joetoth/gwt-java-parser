package com.smashcrush.shell.server.service;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;
import com.smashcrush.shell.shared.FileNode;
import com.smashcrush.shell.shared.Project;
import com.smashcrush.shell.shared.project.ProjectService;

@Singleton
public class ProjectServiceImpl extends RemoteServiceServlet implements
    ProjectService {

  Logger log = Logger.getLogger(ProjectServiceImpl.class.getName());
  
  public Project openProject(String path) {
	Project project = new Project();
	walk(path, project.getFileNodes());
	return project;
  }

  public void walk(String path, List<FileNode> parentFileNodes) {
	log.info(path);
	
	File root = new File(path);
	File[] list = root.listFiles();

	for (File f : list) {
	  FileNode fileNode = new FileNode();
	  fileNode.setPath(f.getAbsolutePath());
	  fileNode.setDirectory(f.isDirectory());
	  parentFileNodes.add(fileNode);
	  if (f.isDirectory()) {
		walk(f.getAbsolutePath(), fileNode.getChildren());
		System.out.println("Dir:" + f.getAbsoluteFile());
	  } else {
		System.out.println("File:" + f.getAbsoluteFile());
	  }
	}

  }

  public void execute(String command) {
	// Stream output to websocket
  }

}
