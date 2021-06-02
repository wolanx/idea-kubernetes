package com.zx5435.idea.kubernetes.tree.node;

import com.intellij.ui.tree.LeafState;

/**
 * @author 913332
 */
public interface ITreeNode {

    public String getLabel();

    public LeafState getLeafState();

}
