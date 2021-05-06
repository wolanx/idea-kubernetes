package com.zx5435.idea.kubernetes;

import com.zx5435.idea.kubernetes.demo.MyToolWindow;
import com.zx5435.idea.kubernetes.node.DeploymentsNode;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author 913332
 */
public class MainTree {

    DefaultTreeModel treeModel;

    public MainTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("default");

        treeModel = new DefaultTreeModel(root, false);

        DefaultMutableTreeNode ns = new DefaultMutableTreeNode("Namespaces");
        DefaultMutableTreeNode wl = new DefaultMutableTreeNode("Workloads");

        treeModel.insertNodeInto(ns, root, root.getChildCount());
        treeModel.insertNodeInto(wl, root, root.getChildCount());

        ns.add(new DefaultMutableTreeNode(new MyToolWindow.User("a")));
        ns.add(new DefaultMutableTreeNode(new MyToolWindow.User("b")));
        ns.add(new DefaultMutableTreeNode(new MyToolWindow.User("c")));

        DeploymentsNode dp = new DeploymentsNode("Deployments");
        DefaultMutableTreeNode pd = new DefaultMutableTreeNode("Pods");

        wl.add(dp);
        wl.add(pd);

        DefaultMutableTreeNode hidden = new DefaultMutableTreeNode("hidden");
        dp.add(hidden);

        // bind
        treeModel.addTreeModelListener(new TreeModelListener() {
            @Override
            public void treeNodesChanged(TreeModelEvent e) {
                System.out.println("treeNodesChanged " + e.toString());
            }

            @Override
            public void treeNodesInserted(TreeModelEvent e) {
                System.out.println("treeNodesInserted " + e.toString());
            }

            @Override
            public void treeNodesRemoved(TreeModelEvent e) {
                System.out.println("treeNodesRemoved " + e.toString());
            }

            @Override
            public void treeStructureChanged(TreeModelEvent e) {
                System.out.println("treeStructureChanged " + e.toString());
            }
        });
    }

}
