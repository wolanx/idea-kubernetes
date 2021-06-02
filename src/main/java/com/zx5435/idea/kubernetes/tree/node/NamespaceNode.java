package com.zx5435.idea.kubernetes.tree.node;

import com.intellij.ui.tree.LeafState;
import lombok.Getter;

/**
 * @author 913332
 */
public class NamespaceNode extends AbstractTreeNode {

    @Getter
    private String label;

    public NamespaceNode(String label) {
        this.label = label;
    }

    @Override
    public LeafState getLeafState() {
        return LeafState.NEVER;
    }

}
