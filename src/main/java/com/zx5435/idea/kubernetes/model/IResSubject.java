package com.zx5435.idea.kubernetes.model;

/**
 * @author 913332
 */
public interface IResSubject {

    void addListener(ITreeObserver listener);
    void fireModified(Object e);

}
