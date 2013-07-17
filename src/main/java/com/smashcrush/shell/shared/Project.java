package com.smashcrush.shell.shared;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class Project implements Serializable {

  List<FileNode> fileNodes = Lists.newArrayList();

  public List<FileNode> getFileNodes() {
	return fileNodes;
  }

  public void setFileNodes(List<FileNode> fileNodes) {
	this.fileNodes = fileNodes;
  }
}
