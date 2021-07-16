package com.zx5435.idea.kubernetes.other;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.ui.components.JBTextField;
import com.zx5435.idea.kubernetes.service.KubeStorage;

import javax.swing.*;
import java.awt.*;

/**
 * @author 913332
 */
public class ClusterAddDialog extends DialogWrapper {

    private JBTextField fName;
    private JBTextArea fContent;

    public ClusterAddDialog() {
        super(true);
        init();
        setTitle("New Cluster from Kubeconfig");
    }

    @Override
    protected JComponent createCenterPanel() {
        JPanel p = new JPanel(new BorderLayout());
        fName = new JBTextField();
//        name.setPlaceholder("cluster name");
        fContent = new JBTextArea(5, 18);
//        content.setPlaceholder("copy .kubeconfig yaml file into here");
        p.add(fName, BorderLayout.NORTH);
        p.add(new JBScrollPane(fContent), BorderLayout.CENTER);
        return p;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel p = new JPanel(new FlowLayout());
        JButton btn = new JButton("Submit");
        btn.addActionListener(e -> {
            KubeStorage storage = ServiceManager.getService(KubeStorage.class);
            storage.createByNameAndContent(fName.getText(), fContent.getText());
            close(0);
        });
        p.add(btn);
        return p;
    }

}