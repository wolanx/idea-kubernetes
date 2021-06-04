package com.zx5435.idea.kubernetes.tree.node;

import com.intellij.ui.tree.LeafState;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author 913332
 */
@Slf4j
public class FolderNode extends AbstractTreeNode {

    @Getter
    private Class<?> kind;
    private final String label;

    public FolderNode(String label) {
        this.label = label;
    }

    public FolderNode(String label, Class<?> kind) {
        this.label = label;
        this.kind = kind;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public LeafState getLeafState() {
        return LeafState.NEVER;
    }

    @Override
    public JPopupMenu getMenu() {
        if (kind != null) {
            JPopupMenu menu = new JPopupMenu();

            JMenuItem b1 = new JMenuItem("Refresh");
            b1.addActionListener(ev -> {
                log.warn("todo");
            });

            menu.add(b1);
            return menu;
        }
        return super.getMenu();
    }

    ////////

    public static class WorkloadsNode extends FolderNode {

        public WorkloadsNode() {
            super("Workloads");
        }

    }

    public static class NetworksNode extends FolderNode {

        public NetworksNode() {
            super("Networks");
        }

    }

    public static class ConfigurationsNode extends FolderNode {

        public ConfigurationsNode() {
            super("Configurations");
        }

    }

    public static class VolumesNode extends FolderNode {

        public VolumesNode() {
            super("Volumes");
        }

    }

}
