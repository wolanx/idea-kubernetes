package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ITreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zx5435
 */
public class ResSubject {

    private final List<ResObserver> obs = new ArrayList<>();

    public void attach(ResObserver observer) {
        obs.add(observer);
    }

    public void notifyUseNs(String ns) {
        for (ResObserver ob : obs) {
            ob.useNs(ns);
        }
    }

    public void notifyNodeChange(ITreeNode node) {
        for (ResObserver one : obs) {
            one.nodeChange(node);
        }
    }

}
