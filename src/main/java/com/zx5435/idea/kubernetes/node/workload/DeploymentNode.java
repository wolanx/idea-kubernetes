package com.zx5435.idea.kubernetes.node.workload;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.JBMenuItem;
import com.intellij.openapi.ui.JBPopupMenu;
import com.zx5435.idea.kubernetes.editor.YamlEditorView;
import com.zx5435.idea.kubernetes.node.ATreeNode;
import com.zx5435.idea.kubernetes.utils.KubeUtil;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author zx5435
 */
@Slf4j
public class DeploymentNode extends ATreeNode {

    Deployment ins;

    public DeploymentNode(Deployment ns) {
        this.ins = ns;
    }

    @Override
    public String getLabel() {
        return ins.getMetadata().getName();
    }

    @Override
    public Icon getIcon() {
        return AllIcons.Nodes.Deploy;
    }

    @Override
    public JBPopupMenu getMenu(Project project) {
        JBPopupMenu menu = new JBPopupMenu();

        JBMenuItem b1 = new JBMenuItem("Load", AllIcons.Actions.Show);
        b1.addActionListener(e -> {
            String yaml = KubeUtil.getDeployment(getCtx().getClient(), ins.getMetadata().getNamespace(), getLabel());
            YamlEditorView.load(project, getLabel(), yaml);
        });
        menu.add(b1);

        JBMenuItem b2 = new JBMenuItem("Delete", AllIcons.Actions.Close);
        b2.addActionListener(e -> {
            log.warn("todo delete");
        });
        menu.add(b2);

        return menu;
    }

}
