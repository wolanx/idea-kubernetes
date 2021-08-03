package com.zx5435.idea.kubernetes.other;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.ui.components.JBTextField;
import com.zx5435.idea.kubernetes.service.StorageSevice;

import javax.swing.*;
import java.awt.*;

/**
 * @author zx5435
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
        fName.getEmptyText().setText("Cluster name");
        p.add(fName, BorderLayout.NORTH);

        fContent = new JBTextArea(5, 18);
        fContent.getEmptyText().setText("Copy .kubeconfig yaml file into here");
        p.add(new JBScrollPane(fContent, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
        return p;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel p = new JPanel(new FlowLayout());

        JButton btn = new JButton("Submit");
        btn.addActionListener(e -> {
            StorageSevice storage = ServiceManager.getService(StorageSevice.class);
            storage.createByNameAndContent(fName.getText(), fContent.getText());
            close(0);
        });
        p.add(btn);

        return p;
    }

}
