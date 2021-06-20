package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ITreeNode;

/**
 * @author 913332
 */
public interface IResSubject {

    void addListener(ITreeObserver listener);

    void fireSelectNs(String ns);

    void fireModified(ITreeNode node);

}
