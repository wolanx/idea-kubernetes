package com.zx5435.idea.kubernetes.tree.model;

import com.zx5435.idea.kubernetes.tree.node.ContextNode;
import com.zx5435.idea.kubernetes.tree.node.NamespaceNode;

import java.util.List;

/**
 * @author 913332
 */
public interface ResourceModel {

    List<ContextNode> getAllContexts();

    List<NamespaceNode> listNs();

}
