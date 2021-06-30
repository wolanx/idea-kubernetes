package com.zx5435.idea.kubernetes.model;

import com.intellij.openapi.components.ServiceManager;
import com.zx5435.idea.kubernetes.node.ClusterNode;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import com.zx5435.idea.kubernetes.service.KubeConfig;
import com.zx5435.idea.kubernetes.service.KubeStorage;
import com.zx5435.idea.kubernetes.service.KubeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 913332
 */
@Slf4j
public class ResModel implements IResModel {

    private final ResSubject observe = new ResSubject();

    private final Map<String, String> ctx2ns = new HashMap<>();

    private final List<ClusterNode> clusters = new ArrayList<>();

    public ResModel() {
        clusters.add(new ClusterNode("default", this));
        clusters.add(new ClusterNode("bbb", this));
    }

    @Override
    public void addListener(ITreeObserver listener) {
        observe.addListener(listener);
    }


    @Override
    public String getNsByCtx(ClusterModel ctx) {
        return ctx2ns.get(ctx.getName());
    }

    @Override
    public void fireSelectNs(ClusterModel ctx, String ns) {
        ctx2ns.put(ctx.getName(), ns);
        observe.fireSelectNs(ns);
    }

    @Override
    public void fireModified(ITreeNode node) {
        observe.fireModified(node);
    }

    @Override
    public List<ClusterNode> getClusters() {
        return clusters;
    }

    @Override
    public void fireCluster() {
        List<KubeConfig> kubeConfigs = ServiceManager.getService(KubeStorage.class).kubeConfigs;
        clusters.clear();
        for (KubeConfig kubeConfig : kubeConfigs) {
            System.out.println(kubeConfig);
            clusters.add(new ClusterNode(kubeConfig.getName(), this));
        }
        observe.fireSelectNs(null);
    }

    @Override
    public void reloadByKind(ITreeNode node, Class<?> kind) {
        this.fireModified(node);
    }

    @Override
    public List<ITreeNode> getResByKind(ITreeNode node, Class<?> kind) {
        return KubeUtil.getByKind(node.getCtx(), kind);
    }

}
