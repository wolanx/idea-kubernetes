package com.zx5435.idea.kubernetes.tree.model;

import com.zx5435.idea.kubernetes.tree.node.ContextNode;
import com.zx5435.idea.kubernetes.tree.node.ITreeNode;

import java.util.List;

/**
 * @author 913332
 */
public interface ResModel {

    List<ContextNode> getAllContexts();

    List<ITreeNode> getResByKind(Class<?> kind);

}
