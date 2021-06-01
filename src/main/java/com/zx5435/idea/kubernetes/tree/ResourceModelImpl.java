package com.zx5435.idea.kubernetes.tree;

import com.zx5435.idea.kubernetes.tree.node.ContextNode;

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

}
