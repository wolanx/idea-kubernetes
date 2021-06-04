package com.zx5435.idea.kubernetes.tree.model;

import com.zx5435.idea.kubernetes.tree.node.ITreeNode;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;

import java.util.List;

/**
 * @author 913332
 */
public class TestModel {

    public static void main(String[] args) {

        ResModelImpl res = new ResModelImpl();
        List<ITreeNode> ret = res.getResByKind(Deployment.class);

        System.out.println();
    }

}
