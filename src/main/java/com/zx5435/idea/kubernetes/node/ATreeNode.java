package com.zx5435.idea.kubernetes.node;

import com.intellij.ui.tree.LeafState;
import com.zx5435.idea.kubernetes.model.IResModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

/**
 * @author 913332
 */
public abstract class ATreeNode implements ITreeNode {

    @Getter
    @Setter
    private String label;

    @Getter
    @Setter
    private IResModel model;

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public JPopupMenu getMenu() {
        return null;
    }

    @Override
    public LeafState getLeafState() {
        return null;
    }

}
