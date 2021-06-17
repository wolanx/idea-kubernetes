package com.zx5435.idea.kubernetes.node;

import com.intellij.ui.tree.LeafState;
import com.zx5435.idea.kubernetes.model.IResModel;
import com.zx5435.idea.kubernetes.model.NsTuple2;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

/**
 * @author 913332
 */
public abstract class ATreeNode implements ITreeNode {

    @Getter
    @Setter
    private NsTuple2 ns;

    @Getter
    @Setter
    private IResModel model;

    @Getter
    @Setter
    private String label;

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
