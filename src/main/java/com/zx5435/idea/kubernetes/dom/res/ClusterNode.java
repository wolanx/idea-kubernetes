package com.zx5435.idea.kubernetes.dom.res;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author 913332
 */
public class ClusterNode extends DefaultMutableTreeNode implements ResNode {

    public ClusterNode(String userObject) {
        super(userObject);

        NamesNode ns = new NamesNode("Namespaces");
        DefaultMutableTreeNode works = new DefaultMutableTreeNode("Workloads");

        add(ns);
        add(works);

        DeploymentsNode dps = new DeploymentsNode("Deployments");
        PodsNode pods = new PodsNode("Pods");

        works.add(dps);
        works.add(pods);
    }

    @Override
    public JPopupMenu getMenu() {
        return null;
    }

}
