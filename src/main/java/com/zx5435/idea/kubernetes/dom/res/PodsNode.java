package com.zx5435.idea.kubernetes.dom.res;

import com.intellij.openapi.components.ServiceManager;
import com.zx5435.idea.kubernetes.dom.MyTree;
import com.zx5435.idea.kubernetes.service.MyApplicationService;
import com.zx5435.idea.kubernetes.service.K8showStorage;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;


/**
 * @author 913332
 */
@Slf4j
public class PodsNode extends DefaultMutableTreeNode implements FolderNode, ResNode {

    public PodsNode(String userObject) {
        super(userObject);

        DefaultMutableTreeNode hidden = new DefaultMutableTreeNode("hidden");
        add(hidden);
    }

    public void treeExpanded() {
        log.warn("treeExpanded");
        MyApplicationService service = ServiceManager.getService(MyApplicationService.class);

        K8showStorage storage = ServiceManager.getService(K8showStorage.class);
        storage.getState();

        removeAllChildren();
        add(new DefaultMutableTreeNode("hidden"));
        for (String s : service.getPod()) {
            add(new PodNode(s));
        }
        MyTree.treeModel.nodeStructureChanged(this);
    }

    @Override
    public JPopupMenu getMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem refreshMenuItem = new JMenuItem("refresh");
        refreshMenuItem.addActionListener(e -> treeExpanded());

        menu.add(refreshMenuItem);
        return menu;
    }

}
