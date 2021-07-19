package com.zx5435.idea.kubernetes.node.workload;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.JBPopupMenu;
import com.zx5435.idea.kubernetes.node.ATreeNode;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author zx5435
 */
@Slf4j
public class DeploymentNode extends ATreeNode {

    Deployment ins;

    public DeploymentNode(Deployment ns) {
        this.ins = ns;
    }

    @Override
    public String getLabel() {
        return ins.getMetadata().getName();
    }

    @Override
    public Icon getIcon() {
        return AllIcons.Nodes.Deploy;
    }

    @Override
    public JBPopupMenu getMenu(Project project) {
        JBPopupMenu menu = new JBPopupMenu();

        JMenuItem b1 = new JMenuItem("delete");
        b1.addActionListener(ev -> {
            log.warn("todo");
        });

        menu.add(b1);
        return menu;
    }

}
