package com.zx5435.idea.kubernetes.node.volume;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.JBPopupMenu;
import com.zx5435.idea.kubernetes.node.ATreeNode;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaim;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author zx5435
 */
@Slf4j
public class PvcNode extends ATreeNode {

    PersistentVolumeClaim ins;

    public PvcNode(PersistentVolumeClaim ns) {
        this.ins = ns;
    }

    @Override
    public String getLabel() {
        return ins.getMetadata().getName();
    }

    @Override
    public Icon getIcon() {
        return AllIcons.Actions.GroupByPrefix;
    }

    @Override
    public JBPopupMenu getMenu(Project project) {
        JBPopupMenu menu = new JBPopupMenu();

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
