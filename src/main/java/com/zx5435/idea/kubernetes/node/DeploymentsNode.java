package com.zx5435.idea.kubernetes.node;

import com.intellij.openapi.components.ServiceManager;
import com.zx5435.idea.kubernetes.demo.MyToolWindow;
import com.zx5435.idea.kubernetes.service.MyApplicationService;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author 913332
 */
public class DeploymentsNode extends DefaultMutableTreeNode {

    public DeploymentsNode(String userObject) {
        super(userObject);
    }

    public void treeExpanded() {
        MyApplicationService service = ServiceManager.getService(MyApplicationService.class);
        System.out.println("service.getDp() = " + service.getDp());

        this.removeAllChildren();
        for (String s : service.getDp()) {
            this.add(new DefaultMutableTreeNode(new MyToolWindow.User(s)));
        }
//        getParent().re
    }


}
