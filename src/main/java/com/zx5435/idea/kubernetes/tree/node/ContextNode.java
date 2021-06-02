package com.zx5435.idea.kubernetes.tree.node;

/**
 * @author 913332
 */
public class ContextNode extends AbstractTreeNode {

    private final String name;

    public ContextNode(String name) {
        this.name = name;
    }

    @Override
    public String getLabel() {
        return this.name;
    }

}
