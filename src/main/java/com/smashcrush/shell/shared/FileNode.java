package com.smashcrush.shell.shared;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class FileNode implements Serializable {

  String path;
  boolean directory;
  List<FileNode> children = Lists.newArrayList();

  public static List<FileNode> of(List<String> files) {
	List<FileNode> fileNodes = Lists.newArrayList();

	for (String file : files) {
	  FileNode fileNode = new FileNode();
	  fileNode.setPath(file);
	  fileNodes.add(fileNode);
	}

	return fileNodes;
  }

  public String getPath() {
	return path;
  }

  public void setPath(String path) {
	this.path = path;
  }

  public boolean isDirectory() {
	return directory;
  }

  public void setDirectory(boolean directory) {
	this.directory = directory;
  }

  public List<FileNode> getChildren() {
	return children;
  }

  public void setChildren(List<FileNode> children) {
	this.children = children;
  }

}
