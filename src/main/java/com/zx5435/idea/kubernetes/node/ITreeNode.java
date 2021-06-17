package com.zx5435.idea.kubernetes.node;

import com.intellij.ui.tree.LeafState;
import com.zx5435.idea.kubernetes.model.NsTuple2;

import javax.swing.*;

/**
 * @author 913332
 */
public interface ITreeNode {

    void setNs(NsTuple2 ns);

    NsTuple2 getNs();

    String getLabel();

    Icon getIcon();

    LeafState getLeafState();

    JPopupMenu getMenu();

}
