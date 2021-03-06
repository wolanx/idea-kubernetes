package com.zx5435.idea.kubernetes;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.treeStructure.Tree;
import com.zx5435.idea.kubernetes.other.ClusterAddDialog;
import com.zx5435.idea.kubernetes.tree.MyTree;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zx5435
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
        AnAction anAction = new AnAction("New Cluster") {
            @Override
            public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
                new ClusterAddDialog().show();
            }
        };
        List<AnAction> arr = new ArrayList<>();
        arr.add(anAction);
        toolWindow.setTitleActions(arr);

        Tree tree = MyTree.createTree(project);
        JScrollPane ctn = ScrollPaneFactory.createScrollPane(tree);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(ctn, "", false);
        toolWindow.getContentManager().addContent(content);
    }

}