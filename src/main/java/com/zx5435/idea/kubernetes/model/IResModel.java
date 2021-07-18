package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ClusterNode;
import com.zx5435.idea.kubernetes.node.ITreeNode;

import java.util.List;

/**
 * @author zx5435
 */
public interface IResModel {

    void addListener(ITreeObserver myTreeUpdater);

    // cluster
    List<ClusterNode> getClusters();
    void fireCluster();

    void reloadByKind(ITreeNode node, Class<?> kind);

    String getNsByCtx(ClusterModel ctx);

    void fireSelectNs(ClusterModel ctx, String ns);

    void fireModified(ITreeNode node);

    List<ITreeNode> getResByKind(ITreeNode node, Class<?> kind);

}
