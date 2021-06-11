package com.zx5435.idea.kubernetes.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 913332
 */
public class ResSubject implements IResSubject {

    private List<ITreeObserver> observers = new ArrayList<>();

    @Override
    public void addListener(ITreeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void fireModified(Object e) {
        for (ITreeObserver one : observers) {
            one.modified();
        }
    }

}
