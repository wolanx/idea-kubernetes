package com.zx5435.idea.kubernetes.tree.model;

import com.zx5435.idea.kubernetes.tree.node.ContextNode;
import com.zx5435.idea.kubernetes.tree.node.NamespaceNode;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 913332
 */
public class ResourceModelImpl implements ResourceModel {

    @Override
    public List<ContextNode> getAllContexts() {
        List<ContextNode> objects = new ArrayList<>();
        objects.add(new ContextNode("aaaa"));
        objects.add(new ContextNode("bbbb"));
        return objects;
    }

    @Override
    public List<NamespaceNode> listNs() {
        DefaultKubernetesClient client = new DefaultKubernetesClient();
        List<Namespace> res = client.namespaces().list().getItems();

        List<NamespaceNode> ret = new ArrayList<>();
        for (Namespace one : res) {
            ret.add(new NamespaceNode(one));
        }
        return ret;
    }

}
