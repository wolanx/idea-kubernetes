package com.zx5435.idea.kubernetes.node;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.batch.v1beta1.CronJob;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author 913332
 */
@Slf4j
public class ConfigMapNode extends AbstractTreeNode {

    ConfigMap ins;

    public ConfigMapNode(ConfigMap ns) {
        this.ins = ns;
    }

    @Override
    public String getLabel() {
        return ins.getMetadata().getName();
    }

    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/img/pod-running.svg");
    }

    @Override
    public JPopupMenu getMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem b1 = new JMenuItem("Load", AllIcons.Actions.Show);
        b1.addActionListener(ev -> {
            log.warn("todo");
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
