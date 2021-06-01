package com.zx5435.idea.kubernetes.tree;

import com.zx5435.idea.kubernetes.tree.node.ContextNode;

import java.util.List;

/**
 * @author 913332
 */
public interface ResourceModel {

    List<ContextNode> getAllContexts();

}
