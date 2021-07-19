package com.zx5435.idea.kubernetes.node;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.JBPopupMenu;
import io.fabric8.kubernetes.api.model.Namespace;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author zx5435
 */
@Slf4j
public class NamespaceNode extends ATreeNode {

    private final Namespace ins;

    private boolean isEmpty = false;

    public NamespaceNode(Namespace ns) {
        this.ins = ns;
        String name = ins.getMetadata().getName();
        if (name == null) {
            isEmpty = true;
        }
    }

    @Override
    public String getLabel() {
        if (isEmpty) {
            return "All namespaces";
        }
        return ins.getMetadata().getName();
    }

    @Override
    public Icon getIcon() {
        String select = getCtx().getNs();
        if (isEmpty) {
            if (select == null) {
                return AllIcons.Actions.GroupByModule;
            } else {
                return AllIcons.Json.Array;
            }
        } else {
            if (getLabel().equals(select)) {
                return AllIcons.Actions.GroupByModule;
            } else {
                return AllIcons.Json.Array;
            }
        }
    }

    @Override
    public JBPopupMenu getMenu(Project project) {
        JBPopupMenu menu = new JBPopupMenu();

        JMenuItem b1 = new JMenuItem("Use namespace");
        b1.addActionListener(e -> {
            getModel().fireSelectNs(getCtx(), ins.getMetadata().getName());
        });

        menu.add(b1);
        return menu;
    }

}
