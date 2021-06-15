package com.zx5435.idea.kubernetes.node;

import com.intellij.ui.tree.LeafState;

import javax.swing.*;
import java.util.List;

/**
 * @author 913332
 */
public interface ITreeNode {

    public String getLabel();

    public Icon getIcon();

    public List<ITreeNode> getPath();

    public LeafState getLeafState();

    public JPopupMenu getMenu();

}
