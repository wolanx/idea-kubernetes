package com.zx5435.idea.kubernetes.node;

import com.intellij.icons.AllIcons;
import com.intellij.ui.tree.LeafState;
import com.zx5435.idea.kubernetes.model.IResModel;
import com.zx5435.idea.kubernetes.model.ResModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author 913332
 */
@Slf4j
public class FolderNode extends AbstractTreeNode {

    @Getter
    @Setter
    private Class<?> kind;

    public FolderNode(String label, ITreeNode parent) {
        setLabel(label);
        setPath(parent.getPath());
    }

    public FolderNode(String label, ITreeNode parent, IResModel model, Class<?> kind) {
        setLabel(label);
        setPath(parent.getPath());
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
                log.warn("todo Refresh");
                ((ResModel) getModel()).getArr().add(new ContextNode("bbbb"));
                ((ResModel) getModel()).fireModified(this);
            });

            menu.add(b1);
            return menu;
        }
        return super.getMenu();
    }

    ////////

    public static class WorkloadsNode extends FolderNode {

        public WorkloadsNode(ITreeNode node) {
            super("Workloads", node);
        }

    }

    public static class NetworksNode extends FolderNode {

        public NetworksNode(ITreeNode node) {
            super("Networks", node);
        }

    }

    public static class ConfigurationsNode extends FolderNode {

        public ConfigurationsNode(ITreeNode node) {
            super("Configurations", node);
        }

    }

    public static class VolumesNode extends FolderNode {

        public VolumesNode(ITreeNode node) {
            super("Volumes", node);
        }

    }

}
