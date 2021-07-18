package com.zx5435.idea.kubernetes.node;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinition;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author zx5435
 */
@Slf4j
public class CustomResDefineNode extends ATreeNode {

    CustomResourceDefinition ins;

    public CustomResDefineNode(CustomResourceDefinition ns) {
        this.ins = ns;
    }

    @Override
    public String getLabel() {
        return ins.getMetadata().getName();
    }

    @Override
    public Icon getIcon() {
        return AllIcons.Nodes.AbstractClass;
    }

    @Override
    public JPopupMenu getMenu(Project project) {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem b1 = new JMenuItem("Load", AllIcons.Actions.Show);
        b1.addActionListener(ev -> {
            log.warn("todo");
        });
        menu.add(b1);

        JMenuItem b2 = new JMenuItem("Delete", AllIcons.Actions.Close);
        b2.addActionListener(ev -> {
            log.warn("todo");
        });
        menu.add(b2);

        return menu;
    }

}
