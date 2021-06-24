package com.zx5435.idea.kubernetes.node;

import com.intellij.icons.AllIcons;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author 913332
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
    public JPopupMenu getMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem b1 = new JMenuItem("delete");
        b1.addActionListener(ev -> {
            log.warn("todo");
        });

        menu.add(b1);
        return menu;
    }

}
