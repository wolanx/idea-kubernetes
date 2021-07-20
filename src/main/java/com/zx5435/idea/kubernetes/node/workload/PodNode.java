package com.zx5435.idea.kubernetes.node.workload;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.JBMenuItem;
import com.intellij.openapi.ui.JBPopupMenu;
import com.intellij.openapi.util.IconLoader;
import com.zx5435.idea.kubernetes.editor.YamlEditorView;
import com.zx5435.idea.kubernetes.node.ATreeNode;
import com.zx5435.idea.kubernetes.utils.KubeUtil;
import io.fabric8.kubernetes.api.model.Pod;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author zx5435
 */
@Slf4j
public class PodNode extends ATreeNode {

    Pod ins;

    public PodNode(Pod ns) {
        this.ins = ns;
    }

    @Override
    public String getLabel() {
        return ins.getMetadata().getName();
    }

    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/img/pod-running.svg", PodNode.class);
    }

    @Override
    public JBPopupMenu getMenu(Project project) {
        JBPopupMenu menu = new JBPopupMenu();

        JBMenuItem b1 = new JBMenuItem("Load", AllIcons.Actions.Show);
        b1.addActionListener(e -> {
            String yaml = KubeUtil.getPod(getCtx().getClient(), "zx5435", getLabel()); // todo
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
