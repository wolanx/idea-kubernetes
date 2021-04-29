package com.zx5435.k8show;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author 913332
 */
public class MainTree {

    DefaultTreeModel treeModel;

    public MainTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("default");
        treeModel = new DefaultTreeModel(root);

        DefaultMutableTreeNode ns = new DefaultMutableTreeNode("Namespaces");
        DefaultMutableTreeNode wl = new DefaultMutableTreeNode("Workloads");

        treeModel.insertNodeInto(ns, root, root.getChildCount());
        treeModel.insertNodeInto(wl, root, root.getChildCount());

        ns.add(new DefaultMutableTreeNode(new MyToolWindow.User("a")));
        ns.add(new DefaultMutableTreeNode(new MyToolWindow.User("b")));
        ns.add(new DefaultMutableTreeNode(new MyToolWindow.User("c")));

        DefaultMutableTreeNode dp = new DefaultMutableTreeNode("Deployments");
        DefaultMutableTreeNode pd = new DefaultMutableTreeNode("Pods");

        wl.add(dp);
        wl.add(pd);

        dp.add(new DefaultMutableTreeNode(new MyToolWindow.User("d")));
    }

}
