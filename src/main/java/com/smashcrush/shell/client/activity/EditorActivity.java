package com.smashcrush.shell.client.activity;

import java.util.logging.Logger;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.smashcrush.shell.client.JavaLexer;
import com.smashcrush.shell.client.JavaParser;
import com.smashcrush.shell.client.view.EditorView;

public class EditorActivity extends BaseActivity<EditorView> {

  Logger log = Logger.getLogger(EditorActivity.class.getName());

  EditorView view;

  @Inject
  public EditorActivity(EditorView view, PlaceController placeController) {
    super(view, placeController);
    this.view = view;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(view);

    Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {

      @Override
      public boolean execute() {
        long time = System.currentTimeMillis();
        parse(view.getText());
        log.info("parsing took: " + (System.currentTimeMillis() - time));
        return true;
      }
    }, 3000);

  }

  private void parse(String text) {
    // ANTLRStringStream s = new ANTLRStringStream(
    // "class Main{ public static void main(String[] args) { } } ");

    ANTLRStringStream s = new ANTLRStringStream(text);

    JavaLexer lexer = new JavaLexer(s);
    // create the buffer of tokens between the lexer and parser
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    // create a debug event listener that builds parse trees
    // ParseTreeBuilder builder = new ParseTreeBuilder("packageDeclaration");
    // create the parser attached to the token buffer
    // and tell it which debug event listener to use
    JavaParser parser = new JavaParser(tokens);
    // parser.setTokenStream(new DebugTokenStream(tokens, builder));

    // JavaLexer lexer = new JavaLexer(input);

    // CommonTokenStream tokens = new CommonTokenStream(lexer);

    // JavaParser parser = new JavaParser(tokens);

    try {
      JavaParser.javaSource_return r = parser.javaSource();

      CommonTree t = (CommonTree) r.getTree();
      visit(t);
    } catch (RecognitionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void visit(Object t) {
    // System.out.println("Visiting: " + t.getClass());
    if (t instanceof Tree) {
      Tree tree = (Tree) t;
//      log.info(tree.getLine() + ":" + tree.getCharPositionInLine() + " -> "
//          + tree.getText() + " of type " + tree.getType());
      for (int i = 0; i < tree.getChildCount(); i++) {
        visit(tree.getChild(i));
      }
    }

  }

  @Override
  public String mayStop() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void onCancel() {
    // TODO Auto-generated method stub

  }

  @Override
  public void onStop() {
    // TODO Auto-generated method stub

  }

}
