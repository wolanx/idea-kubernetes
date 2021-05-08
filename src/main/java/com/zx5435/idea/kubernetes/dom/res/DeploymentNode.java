package com.zx5435.idea.kubernetes.dom.res;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author 913332
 */
public class DeploymentNode extends DefaultMutableTreeNode implements ResNode {

    public DeploymentNode(String userObject) {
        super(userObject);
    }

    @Override
    public JPopupMenu getMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem refreshMenuItem = new JMenuItem("delete");
        refreshMenuItem.addActionListener(ev -> System.out.println("delete!"));

        menu.add(refreshMenuItem);
        return menu;
    }

}
