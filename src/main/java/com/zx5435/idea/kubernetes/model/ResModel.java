package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ClusterNode;
import com.zx5435.idea.kubernetes.node.ITreeNode;
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

    public ResModel() {
        //
    }

    @Override
    public void addListener(ITreeObserver listener) {
        observe.addListener(listener);
    }


    @Override
    public String getNsByCtx(Cluster ctx) {
        return ctx2ns.get(ctx.getName());
    }

    @Override
    public void fireSelectNs(Cluster ctx, String ns) {
        ctx2ns.put(ctx.getName(), ns);
        observe.fireSelectNs(ns);
    }

    public void fireModified(ITreeNode node) {
        observe.fireModified(node);
    }

    @Override
    public List<ClusterNode> getAllContexts() {
        List<ClusterNode> ret = new ArrayList<>();
        ret.add(new ClusterNode("default", this));
        ret.add(new ClusterNode("bbb", this));
        return ret;
    }

    public void reloadByKind(ITreeNode node, Class<?> kind) {
        //this.arr.add(new ContextNode("bbbb"));
        this.fireModified(node);
    }

    @Override
    public List<ITreeNode> getResByKind(ITreeNode node, Class<?> kind) {
        System.out.println(node.getCtx());
        return KubeUtil.getByKind(node.getCtx(), kind);
    }

}
