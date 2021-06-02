package com.zx5435.idea.kubernetes.tree.node;

import com.intellij.ui.tree.LeafState;
import lombok.Getter;

/**
 * @author 913332
 */
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

    //

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
