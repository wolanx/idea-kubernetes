package com.zx5435.idea.kubernetes.node;

import com.zx5435.idea.kubernetes.model.NsTuple2;

/**
 * @author 913332
 */
public class ContextNode extends ATreeNode {

    public ContextNode(String label) {
        setNs(NsTuple2.of(label, null));
        setLabel(label);
    }

}
