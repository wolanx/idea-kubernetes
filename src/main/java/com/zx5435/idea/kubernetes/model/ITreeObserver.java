package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ITreeNode;

/**
 * @author 913332
 */
public interface ITreeObserver {

    void added();

    void removed();

    void modified(ITreeNode node);

}
