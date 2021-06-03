package com.zx5435.idea.kubernetes.tree.node;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import lombok.Getter;

/**
 * @author 913332
 */
public class NamespaceNode extends AbstractTreeNode implements HasMetadata {

    @Getter
    private String label;

    public NamespaceNode(Namespace ns) {
        this.label = ns.getMetadata().getName();
    }

    @Override
    public ObjectMeta getMetadata() {
        return null;
    }

    @Override
    public void setMetadata(ObjectMeta metadata) {

    }

    @Override
    public void setApiVersion(String version) {

    }

}
