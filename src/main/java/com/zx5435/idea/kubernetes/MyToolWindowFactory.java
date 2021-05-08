package com.zx5435.idea.kubernetes;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.treeStructure.Tree;
import com.zx5435.idea.kubernetes.dom.MyTree;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

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
        Tree tree = createTree();
        JScrollPane ctn = ScrollPaneFactory.createScrollPane(tree);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(ctn, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private Tree createTree() {
        DefaultTreeModel treeModel = MyTree.initModel();

        Tree tree = new Tree(treeModel);
        tree = MyTree.bindAction(tree, treeModel);

        return tree;
    }

}