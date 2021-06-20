package com.zx5435.idea.kubernetes.node;

import com.zx5435.idea.kubernetes.model.Cluster;
import com.zx5435.idea.kubernetes.model.IResModel;

/**
 * @author 913332
 */
public class ClusterNode extends ATreeNode {

    public ClusterNode(String label, IResModel model) {
        setLabel(label);
        setModel(model);
        this.setCtx(Cluster.of(label, getModel()));
    }

}
