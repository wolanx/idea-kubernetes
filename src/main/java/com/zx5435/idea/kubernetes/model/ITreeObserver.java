package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ITreeNode;
import io.fabric8.kubernetes.api.model.Namespace;

/**
 * @author zx5435
 */
public interface ITreeObserver {

    void selectNs(String ns);

    void added();

    void removed();

    void modified(ITreeNode node);

}
