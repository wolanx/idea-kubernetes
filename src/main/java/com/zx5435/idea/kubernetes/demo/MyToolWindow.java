package com.zx5435.idea.kubernetes.demo;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.treeStructure.Tree;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.Config;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.IOException;

/**
 * @author 913332
 */
public class MyToolWindow {

    private JButton refreshToolWindowButton;
    private JPanel myToolWindowContent;
    private JList<String> list1;
    private JTree tree;

    DefaultTreeModel treeModel;

    DefaultMutableTreeNode root;
    DefaultMutableTreeNode node2;

    private void createUIComponents() {
        root = new DefaultMutableTreeNode("default");
        treeModel = new DefaultTreeModel(root);

        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("namespace");
        node2 = new DefaultMutableTreeNode("deployments");

        treeModel.insertNodeInto(node1, root, root.getChildCount());
        treeModel.insertNodeInto(node2, root, root.getChildCount());

        node1.add(new DefaultMutableTreeNode(new User("a")));
        node1.add(new DefaultMutableTreeNode(new User("b")));
        node1.add(new DefaultMutableTreeNode(new User("c")));
        node2.add(new DefaultMutableTreeNode(new User("a")));
        node2.add(new DefaultMutableTreeNode(new User("s")));
        node2.add(new DefaultMutableTreeNode(new User("d")));

        tree = new Tree(treeModel);
    }

    MyToolWindow(ToolWindow toolWindow) {
        refreshToolWindowButton.addActionListener(e -> currentDateTime());
    }

    private void currentDateTime() {
        DefaultListModel<String> dlm = new DefaultListModel<>();

        node2.removeAllChildren();

        ApiClient client = null;
        String kubeconfig = getClass().getClassLoader().getResource("kubeconfig").getFile();
        System.out.println("kubeconfig = " + kubeconfig);
        try {
            client = Config.fromConfig("C:\\Users\\913332\\kubeconfig");
            Configuration.setDefaultApiClient(client);

            CoreV1Api api = new CoreV1Api();
            V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
            for (V1Pod item : list.getItems()) {
                String depName = item.getMetadata().getName() + "asdasdasdasd";
                System.out.println("depName = " + depName);
                dlm.addElement(depName);
                node2.add(new DefaultMutableTreeNode(new User(depName)));
            }
        } catch (IOException | ApiException e) {
            e.printStackTrace();
        }

        treeModel.reload();

        list1.setModel(dlm);
    }

    JPanel getContent() {
        return myToolWindowContent;
    }

    public static class User {

        private final String name;

        public User(String n) {
            name = n;
        }

        @Override
        public String toString() {
            return name;
        }

    }

}
