package com.zx5435.idea.kubernetes.node;

import com.intellij.ui.tree.LeafState;

import javax.swing.*;

/**
 * @author 913332
 */
public abstract class AbstractTreeNode implements ITreeNode {

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public JPopupMenu getMenu() {
        return null;
    }

    @Override
    public LeafState getLeafState(){
        return null;
    }

}
