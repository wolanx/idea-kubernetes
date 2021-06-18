package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ContextNode;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import com.zx5435.idea.kubernetes.service.KbsUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 913332
 */
@Slf4j
public class ResModel implements IResModel {

    private final ResSubject observe = new ResSubject();

    public ResModel() {
        //
    }

    @Override
    public void addListener(ITreeObserver listener) {
        observe.addListener(listener);
    }

    public void fireModified(ITreeNode node) {
        observe.fireModified(node);
    }

    @Override
    public List<ContextNode> getAllContexts() {
        List<ContextNode> ret = new ArrayList<>();
        ret.add(new ContextNode("default"));
        ret.add(new ContextNode("bbb"));
        return ret;
    }

    public void reloadByKind(ITreeNode node, Class<?> kind) {
        //this.arr.add(new ContextNode("bbbb"));
        this.fireModified(node);
    }

    @Override
    public List<ITreeNode> getResByKind(ITreeNode node, Class<?> kind) {
        System.out.println(node.getNs());
        return KbsUtil.getByKind(node.getNs(), kind);
    }

}
