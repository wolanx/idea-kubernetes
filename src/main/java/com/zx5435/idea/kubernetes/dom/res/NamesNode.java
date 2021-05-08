package com.zx5435.idea.kubernetes.dom.res;

import com.intellij.openapi.components.ServiceManager;
import com.zx5435.idea.kubernetes.service.MyApplicationService;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author 913332
 */
public class NamesNode extends DefaultMutableTreeNode implements FolderNode {

    public NamesNode(String userObject) {
        super(userObject);

        DefaultMutableTreeNode hidden = new DefaultMutableTreeNode("hidden");
        add(hidden);
    }

    public void treeExpanded() {
        MyApplicationService service = ServiceManager.getService(MyApplicationService.class);

        this.removeAllChildren();
        for (String s : service.getNamespace()) {
            this.add(new DefaultMutableTreeNode(s));
        }
    }

}
