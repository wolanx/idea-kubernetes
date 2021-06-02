package com.zx5435.idea.kubernetes.tree.node;

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

}
