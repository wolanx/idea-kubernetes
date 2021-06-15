package com.zx5435.idea.kubernetes.node;

import com.intellij.ui.tree.LeafState;
import com.zx5435.idea.kubernetes.model.IResModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 913332
 */
public abstract class AbstractTreeNode implements ITreeNode {

    @Getter
    @Setter
    private String label;

    @Getter
    @Setter
    private IResModel model;

    @Getter
    private List<ITreeNode> path = null;

    @Override
    public Icon getIcon() {
        return null;
    }

    public void setPath(List<ITreeNode> path) {
        if (path == null) {
            this.path = new ArrayList<>();
        } else {
            this.path = new ArrayList<>(path); // 浅拷贝
        }
        this.path.add(this);
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
