package com.zx5435.idea.kubernetes.dom.res;

import com.intellij.openapi.components.ServiceManager;
import com.zx5435.idea.kubernetes.service.MyApplicationService;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author 913332
 */
public class DeploymentsNode extends DefaultMutableTreeNode implements FolderNode,ResNode {

    public DeploymentsNode(String userObject) {
        super(userObject);

        DefaultMutableTreeNode hidden = new DefaultMutableTreeNode("hidden");
        add(hidden);
    }

    public void treeExpanded() {
        MyApplicationService service = ServiceManager.getService(MyApplicationService.class);

        this.removeAllChildren();
        for (String s : service.getDeploy()) {
            add(new DeploymentNode(s));
        }
    }

    @Override
    public JPopupMenu getMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem refreshMenuItem = new JMenuItem("refresh");
        refreshMenuItem.addActionListener(ev -> System.out.println("refresh!"));

        menu.add(refreshMenuItem);
        return menu;
    }

}
