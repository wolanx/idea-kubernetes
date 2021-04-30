package com.zx5435.idea.kubernetes;

import com.intellij.ide.util.treeView.NodeRenderer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.treeStructure.Tree;
import com.zx5435.idea.kubernetes.node.DeploymentsNode;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;

/**
 * @author 913332
 */
public class MyToolWindowFactory implements ToolWindowFactory {

    /**
     * Create the tool window content.
     *
     * @param project    current project
     * @param toolWindow current tool window
     */
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
//        MyToolWindow myToolWindow = new MyToolWindow(toolWindow);
//        JComponent ctn = myToolWindow.getContent();

        Tree tree = createTree();
        JScrollPane ctn = ScrollPaneFactory.createScrollPane(tree);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(ctn, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private Tree createTree() {
        MainTree mainTree = new MainTree();

        Tree tree = new Tree(mainTree.treeModel);
        tree.addTreeExpansionListener(new TreeExpansionListener() {

            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                Object lastPathComponent = event.getPath().getLastPathComponent();
                System.out.println("treeExpanded = " + lastPathComponent);

                if (lastPathComponent instanceof DeploymentsNode) {
                    ((DeploymentsNode) lastPathComponent).treeExpanded();
                    tree.updateUI();
                }
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                System.out.println("treeCollapsed");
            }
        });

        tree.setCellRenderer(new NodeRenderer());

        return tree;
    }

}