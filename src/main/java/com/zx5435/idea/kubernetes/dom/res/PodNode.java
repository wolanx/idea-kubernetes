package com.zx5435.idea.kubernetes.dom.res;

import com.zx5435.idea.kubernetes.service.MyApplicationServiceImpl;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author 913332
 */
public class PodNode extends DefaultMutableTreeNode implements ResNode {

    public PodNode(String userObject) {
        super(userObject);
    }

    @Override
    public JPopupMenu getMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem refreshMenuItem = new JMenuItem("delete");
        refreshMenuItem.addActionListener(e -> {
            MyApplicationServiceImpl.deletePod(userObject.toString());
            System.out.println(this.parent);
            if (parent instanceof FolderNode) {
                ((FolderNode) parent).treeExpanded();
            }
        });

        menu.add(refreshMenuItem);
        return menu;
    }

}
