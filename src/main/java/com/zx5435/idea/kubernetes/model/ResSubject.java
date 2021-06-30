package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ITreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 913332
 */
public class ResSubject implements IResSubject {

    private final List<ITreeObserver> observers = new ArrayList<>();

    @Override
    public void addListener(ITreeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void fireSelectNs(String ns) {
        for (ITreeObserver one : observers) {
            one.selectNs(ns);
        }
    }

    @Override
    public void fireModified(ITreeNode node) {
        for (ITreeObserver one : observers) {
            one.modified(node);
        }
    }

}
