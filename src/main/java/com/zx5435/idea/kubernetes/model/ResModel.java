package com.zx5435.idea.kubernetes.model;

import com.intellij.openapi.components.ServiceManager;
import com.zx5435.idea.kubernetes.node.ClusterNode;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import com.zx5435.idea.kubernetes.service.StorageSevice;
import com.zx5435.idea.kubernetes.utils.KubeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zx5435
 */
@Slf4j
public class ResModel implements IResModel {

    private final ResSubject im = new ResSubject();

    private final List<ClusterNode> clusters = new ArrayList<>();
    private final Map<String, String> selectedNs = new HashMap<>();

    public ResModel() {
        this.loadClusters();
    }

    @Override
    public void addListener(ResObserver ob) {
        im.attach(ob);
    }

    @Override
    public void loadClusters() {
        List<KubeConfig> kubeConfigs = ServiceManager.getService(StorageSevice.class).kubeConfigs;
        clusters.clear();
        for (KubeConfig kubeConfig : kubeConfigs) {
            clusters.add(new ClusterNode(new ClusterModel(kubeConfig), this));
        }
        im.notifyUseNs(null);
    }

    @Override
    public List<ClusterNode> getClusters() {
        return clusters;
    }

    @Override
    public String getNsByClusterName(String clusterName) {
        return selectedNs.get(clusterName);
    }

    @Override
    public void useNs(ClusterModel ctx, String ns) {
        selectedNs.put(ctx.getName(), ns);
        im.notifyUseNs(ns);
    }

    @Override
    public void nodeChange(ITreeNode node) {
        im.notifyNodeChange(node);
    }

    @Override
    public void reloadByKind(ITreeNode node, Class<?> kind) {
        this.nodeChange(node);
    }

    @Override
    public List<ITreeNode> getResByKind(ITreeNode node, Class<?> kind) {
        return KubeUtil.getByKind(node.getCtx(), kind);
    }

}
