package com.zx5435.idea.kubernetes.other;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.ui.EditorTextField;
import com.zx5435.idea.kubernetes.service.K8showStorage;

import javax.swing.*;
import java.awt.*;

public class AddClusterDialog extends DialogWrapper {

    private EditorTextField name;
    private EditorTextField content;

    public AddClusterDialog() {
        super(true);
        init();
        setTitle("New Kubeconfig");
    }

    @Override
    protected JComponent createCenterPanel() {
        JPanel p = new JPanel(new BorderLayout());
        name = new EditorTextField("cluster name");
        content = new EditorTextField("copy .kubeconfig yaml file into here");
        content.setOneLineMode(false);
        content.setAutoscrolls(true);
        content.setPreferredSize(new Dimension(200, 100));
        p.add(name, BorderLayout.NORTH);
        p.add(content, BorderLayout.CENTER);
        return p;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel p = new JPanel(new FlowLayout());
        JButton btn = new JButton("Submit");
        btn.addActionListener(e -> {
            K8showStorage storage = ServiceManager.getService(K8showStorage.class);
            storage.saveAny(name.getText(), content.getText());
            MessageDialogBuilder.okCancel("操作结果", "添加成功!").guessWindowAndAsk();
            this.close(0);
        });
        p.add(btn);
        return p;
    }

}
