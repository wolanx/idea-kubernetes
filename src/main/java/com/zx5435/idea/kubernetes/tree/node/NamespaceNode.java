package com.zx5435.idea.kubernetes.tree.node;

import io.fabric8.kubernetes.api.model.Namespace;

/**
 * @author 913332
 */
public class NamespaceNode extends AbstractTreeNode {

    Namespace ins;

    public NamespaceNode(Namespace ns) {
        this.ins = ns;
    }

    @Override
    public String getLabel() {
        return ins.getMetadata().getName();
    }

}
