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
public class ClusterEditDialog extends DialogWrapper {

    private final String nameOld;
    private final JBTextField fName;
    private final JBTextArea fContent;

    public ClusterEditDialog(String name, String content) {
        super(true);

        nameOld = name;
        fName = new JBTextField(name);
        fContent = new JBTextArea(5, 18);
        fContent.setText(content);

        init();
        setTitle("Edit Cluster");
    }

    @Override
    protected JComponent createCenterPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(fName, BorderLayout.NORTH);
        p.add(new JBScrollPane(fContent), BorderLayout.CENTER);
        return p;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel p = new JPanel(new FlowLayout());
        JButton btn = new JButton("Save");
        btn.addActionListener(e -> {
            StorageSevice storage = ServiceManager.getService(StorageSevice.class);
            storage.updateByNameAndContent(nameOld, fName.getText(), fContent.getText());
            close(0);
        });
        p.add(btn);
        return p;
    }

}
