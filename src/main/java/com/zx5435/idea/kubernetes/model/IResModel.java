package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ClusterNode;
import com.zx5435.idea.kubernetes.node.ITreeNode;

import java.util.List;

/**
 * @author 913332
 */
public interface IResModel {

    void addListener(ITreeObserver myTreeUpdater);

    List<ClusterNode> getAllContexts();

    void reloadByKind(ITreeNode node, Class<?> kind);

    String getNsByCtx(Cluster ctx);

    void fireSelectNs(Cluster ctx,String ns);

    void fireModified(ITreeNode node);

    List<ITreeNode> getResByKind(ITreeNode node, Class<?> kind);

}
