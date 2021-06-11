package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ContextNode;
import com.zx5435.idea.kubernetes.node.ITreeNode;
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
    private ResSubject observe = new ResSubject();

    public ResModel() {
        arr = new ArrayList<>();
        arr.add(new ContextNode("aaa"));
    }

    @Override
    public void addListener(ITreeObserver listener) {
        observe.addListener(listener);
    }

    public void fireModified() {
        observe.fireModified(null);
    }

    @Override
    public List<ContextNode> getAllContexts() {
        List<ContextNode> ret = new ArrayList<>();
        ret.add(new ContextNode("aaaa"));
        ret.add(new ContextNode("bbbb"));
        return ret;
    }

    @Override
    public List<ITreeNode> getResByKind(Class<?> kind) {
        // KbsUtil
        return arr;
    }

}
