package com.smashcrush.shell.client.view.navigation;

import java.util.logging.Logger;

import com.google.common.base.Preconditions;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.smashcrush.shell.client.files.FileManager;
import com.smashcrush.shell.shared.FileNode;

@Singleton
public class NavigationView extends Composite {

  Logger log = Logger.getLogger("aa");

  @UiTemplate("NavigationView.ui.xml")
  public interface Binder extends UiBinder<Widget, NavigationView> {
  }

  @UiField(provided = true)
  CellTree navigationTree;

  @UiField
  Button bzz;

  private FileManager fileManager;

  ListDataProvider<FileNode> dataProvider = new ListDataProvider<FileNode>();

  @Inject
  public NavigationView(Binder binder, FileManager fileManager) {
    this.fileManager = Preconditions.checkNotNull(fileManager);
    createTree();
    initWidget(binder.createAndBindUi(this));
  }

  @UiHandler("bzz")
  protected void onclick(ClickEvent clickEvent) {
//    FileNode f = new FileNode();
//    f.setDirectory(true);
//    f.setPath("pathttt");
    log.fine("bzzzzz");
     dataProvider.getList().addAll(fileManager.getFileNodes());
//    dataProvider.refresh();
  }

  private void createTree() {
    log.fine("created a tree");
    navigationTree = new CellTree(createViewModel(), null) {
      @Override
      public int getDefaultNodeSize() {
        return Integer.MAX_VALUE;
      }
    };
  }

  private TreeViewModel createViewModel() {
    return new TreeViewModel() {

      @Override
      public boolean isLeaf(Object value) {
        if (value == null) {
          return false;
        }
        return !((FileNode) value).isDirectory();
      }

      @Override
      public <X> NodeInfo<?> getNodeInfo(X value) {

        ListDataProvider<FileNode> dp = null;
        
        if (value == null) {
          dp = dataProvider;
          log.fine("lift off" + dataProvider.getList().size());
        }

        log.fine("sup? " + value);

        return new DefaultNodeInfo<FileNode>(dataProvider, createCell());
      }
    };
  }
  
  private AbstractCell<FileNode> createCell() {
    return new AbstractCell<FileNode>() {
      
      @Override
      public void render(Context context, FileNode value, SafeHtmlBuilder sb) {
        sb.appendEscaped(value.getPath());
        
      }
    };
  }
}