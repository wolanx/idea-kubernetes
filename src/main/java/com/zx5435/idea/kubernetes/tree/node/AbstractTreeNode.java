package com.zx5435.idea.kubernetes.tree.node;

import com.intellij.ui.tree.LeafState;

/**
 * @author 913332
 */
public abstract class AbstractTreeNode implements ITreeNode {

    @Override
    public LeafState getLeafState(){
        return null;
    }

}
