package com.zx5435.idea.kubernetes.node.workload;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.JBPopupMenu;
import com.zx5435.idea.kubernetes.editor.YamlEditorView;
import com.zx5435.idea.kubernetes.node.ATreeNode;
import com.zx5435.idea.kubernetes.utils.KubeUtil;
import io.fabric8.kubernetes.api.model.batch.v1.Job;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author zx5435
 */
@Slf4j
public class JobNode extends ATreeNode {

    Job ins;

    public JobNode(Job ns) {
        this.ins = ns;
    }

    @Override
    public String getLabel() {
        return ins.getMetadata().getName();
    }

    @Override
    public Icon getIcon() {
        return AllIcons.Actions.Run_anything;
    }

    @Override
    public JBPopupMenu getMenu(Project project) {
        JBPopupMenu menu = new JBPopupMenu();

        JMenuItem b1 = new JMenuItem("Load", AllIcons.Actions.Show);
        b1.addActionListener(ev -> {
            String yaml = KubeUtil.getCronJob(getCtx().getClient(), "zx5435", getLabel()); // todo
            YamlEditorView.load(project, getLabel(), yaml);
        });
        menu.add(b1);

        JMenuItem b2 = new JMenuItem("Delete", AllIcons.Actions.Close);
        b2.addActionListener(ev -> {
            log.warn("todo");
        });
        menu.add(b2);

        return menu;
    }

}
