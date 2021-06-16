package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ContextNode;
import com.zx5435.idea.kubernetes.node.ITreeNode;

import java.util.List;

/**
 * @author 913332
 */
public interface IResModel {

    void addListener(ITreeObserver myTreeUpdater);

    List<ContextNode> getAllContexts();

    void reloadByKind(ITreeNode node, Class<?> kind);

    List<ITreeNode> getResByKind(Class<?> kind);

}
