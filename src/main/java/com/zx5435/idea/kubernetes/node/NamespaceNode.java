package com.zx5435.idea.kubernetes.node;

import com.intellij.icons.AllIcons;
import io.fabric8.kubernetes.api.model.Namespace;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author 913332
 */
@Slf4j
public class NamespaceNode extends ATreeNode {

    Namespace ins;

    public NamespaceNode(Namespace ns) {
        this.ins = ns;
    }

    @Override
    public String getLabel() {
        String name = ins.getMetadata().getName();
        return name != null ? name : "all";
    }

    @Override
    public Icon getIcon() {
        String ns = getCtx().getNs();
        if (getLabel().equals(ns) || (getLabel().equals("all") && ns == null)) {
            return AllIcons.Actions.Execute;
        } else {
            return AllIcons.Nodes.EmptyNode;
        }
    }

    @Override
    public JPopupMenu getMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem b1 = new JMenuItem("Use namespace");
        b1.addActionListener(e -> {
            getModel().fireSelectNs(getCtx(), ins.getMetadata().getName());
        });

        menu.add(b1);
        return menu;
    }

}
