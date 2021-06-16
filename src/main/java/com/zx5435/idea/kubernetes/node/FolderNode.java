package com.zx5435.idea.kubernetes.node;

import com.intellij.icons.AllIcons;
import com.intellij.ui.tree.LeafState;
import com.zx5435.idea.kubernetes.model.IResModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author 913332
 */
@Slf4j
public class FolderNode extends ATreeNode {

    @Getter
    @Setter
    private Class<?> kind;

    public FolderNode(String label) {
        setLabel(label);
    }

    public FolderNode(String label, IResModel model, Class<?> kind) {
        setLabel(label);
        setModel(model);
        setKind(kind);
    }

    @Override
    public LeafState getLeafState() {
        return LeafState.NEVER;
    }

    @Override
    public JPopupMenu getMenu() {
        if (kind != null) {
            JPopupMenu menu = new JPopupMenu();

            JMenuItem b1 = new JMenuItem("Refresh", AllIcons.Actions.Refresh);
            b1.addActionListener(e -> {
                log.warn("refresh");
                getModel().reloadByKind(this, kind);
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
