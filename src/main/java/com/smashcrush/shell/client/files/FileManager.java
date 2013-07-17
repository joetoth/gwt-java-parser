package com.smashcrush.shell.client.files;

import java.util.List;
import java.util.logging.Logger;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.smashcrush.shell.shared.FileNode;

@Singleton
public class FileManager {

  Logger log = Logger.getLogger("fm");

  List<FileNode> fileNodes = Lists.newArrayList();

  @Inject
  public FileManager() {

  }

  public void setFileNodes(List<FileNode> fileNodes) {
    log.fine(fileNodes.size() + " - from server");
    this.fileNodes.clear();
    this.fileNodes.addAll(fileNodes);
  }

  public List<FileNode> getFileNodes() {
    return fileNodes;
  }

  public List<FileNode> search(String regex) {
    Builder<FileNode> builder = ImmutableList.builder();

    for (FileNode file : fileNodes) {
      if (file.getPath().contains(regex)) {
        builder.add(file);
      }
    }

    return builder.build();
  }

}
