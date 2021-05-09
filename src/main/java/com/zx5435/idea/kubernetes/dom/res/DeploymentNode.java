package com.zx5435.idea.kubernetes.dom.res;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author 913332
 */
@Slf4j
public class DeploymentNode extends DefaultMutableTreeNode implements ResNode {

    public DeploymentNode(String userObject) {
        super(userObject);
    }

    @Override
    public JPopupMenu getMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem refreshMenuItem = new JMenuItem("delete");
        refreshMenuItem.addActionListener(ev -> {
            log.warn("delete!");
        });

        menu.add(refreshMenuItem);
        return menu;
    }

}
