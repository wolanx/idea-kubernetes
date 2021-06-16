package com.zx5435.idea.kubernetes.node;

import com.intellij.ui.tree.LeafState;

import javax.swing.*;

/**
 * @author 913332
 */
public interface ITreeNode {

    public String getLabel();

    public Icon getIcon();

    public LeafState getLeafState();

    public JPopupMenu getMenu();

}
