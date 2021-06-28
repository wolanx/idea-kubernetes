package com.zx5435.idea.kubernetes.node;

import com.zx5435.idea.kubernetes.model.Cluster;
import com.zx5435.idea.kubernetes.model.IResModel;
import io.fabric8.kubernetes.api.model.Namespace;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 913332
 */
public class ClusterNode extends ATreeNode {

    @Getter
    private final List<ITreeNode> childElements = new ArrayList<>();

    public ClusterNode(String label, IResModel model) {
        setLabel(label);
        setModel(model);
        this.setCtx(new Cluster(label, getModel()));

        childElements.add(new FolderNode("Namespaces", getModel(), Namespace.class));
        childElements.add(new FolderNode.WorkloadsNode());
        childElements.add(new FolderNode.NetworksNode());
        childElements.add(new FolderNode.ConfigurationsNode());
        childElements.add(new FolderNode.VolumesNode());
    }

}
