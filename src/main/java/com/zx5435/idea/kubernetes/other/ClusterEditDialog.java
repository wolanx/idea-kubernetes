package com.zx5435.idea.kubernetes.other;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.EditorTextField;
import com.zx5435.idea.kubernetes.service.K8showStorage;

import javax.swing.*;
import java.awt.*;

/**
 * @author 913332
 */
public class ClusterEditDialog extends DialogWrapper {

    private final EditorTextField name;
    private final EditorTextField content;

    public ClusterEditDialog(String name, String content) {
        super(true);

        this.name = new EditorTextField(name);
        this.content = new EditorTextField(content);
        this.content.setAutoscrolls(true);
        this.content.setPreferredSize(new Dimension(200, 100));

        init();
        setTitle("Edit Cluster");
    }

    @Override
    protected JComponent createCenterPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(name, BorderLayout.NORTH);
        p.add(content, BorderLayout.CENTER);
        return p;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel p = new JPanel(new FlowLayout());
        JButton btn = new JButton("Save");
        btn.addActionListener(e -> {
            K8showStorage storage = ServiceManager.getService(K8showStorage.class);
            storage.updateByNameAndContent(name.getText(), content.getText());
            close(0);
        });
        p.add(btn);
        return p;
    }

}
