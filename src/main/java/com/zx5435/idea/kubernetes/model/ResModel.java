package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ContextNode;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import com.zx5435.idea.kubernetes.service.KbsUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 913332
 */
@Slf4j
public class ResModel implements IResModel {

    @Getter
    private List<ITreeNode> arr;
    private final ResSubject observe = new ResSubject();

    public ResModel() {
        arr = new ArrayList<>();
        arr.add(new ContextNode("aaa"));
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
        ret.add(new ContextNode("aaaa"));
        ret.add(new ContextNode("bbbb"));
        return ret;
    }

    public void reloadByKind(ITreeNode node, Class<?> kind) {
        this.arr.add(new ContextNode("bbbb"));
        this.fireModified(node);
    }

    @Override
    public List<ITreeNode> getResByKind(Class<?> kind) {
//        return arr;
        return KbsUtil.getByKind(kind);
    }

}
