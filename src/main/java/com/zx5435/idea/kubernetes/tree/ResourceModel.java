package com.zx5435.idea.kubernetes.tree;

import com.zx5435.idea.kubernetes.dom.res.ClusterNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 913332
 */
public class ResourceModel implements IResourceModel {

    @Override
    public List<Context> getAllContexts() {
        List<Context> objects = new ArrayList<>();
        objects.add(new Context("aaaa"));
        objects.add(new Context("bbbb"));
        return objects;
    }

}
