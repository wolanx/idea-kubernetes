package com.zx5435.k8show;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.treeStructure.Tree;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author 913332
 */
public class K8sToolWindowFactory implements ToolWindowFactory {

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
                DefaultMutableTreeNode lastPathComponent = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                System.out.println("treeExpanded" + lastPathComponent.getUserObject());
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                System.out.println("treeCollapsed");
            }
        });

        return tree;
    }

}