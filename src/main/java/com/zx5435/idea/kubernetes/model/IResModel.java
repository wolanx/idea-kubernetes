package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ClusterNode;
import com.zx5435.idea.kubernetes.node.ITreeNode;

import java.util.List;

/**
 * @author zx5435
 */
public interface IResModel {

    void addListener(ResObserver ob);

    // cluster
    List<ClusterModel> getClusters();
    void loadClusters();

    void reloadByKind(ITreeNode node, Class<?> kind);

    String getNsByCtx(ClusterModel ctx);

    void useNs(ClusterModel ctx, String ns);

    void nodeChange(ITreeNode node);

    List<ITreeNode> getResByKind(ITreeNode node, Class<?> kind);

}
