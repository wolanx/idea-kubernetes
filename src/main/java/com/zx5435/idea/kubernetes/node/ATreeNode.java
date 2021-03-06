package com.zx5435.idea.kubernetes.node;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.JBPopupMenu;
import com.intellij.ui.tree.LeafState;
import com.zx5435.idea.kubernetes.model.ClusterModel;
import com.zx5435.idea.kubernetes.model.IResModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.List;

/**
 * @author zx5435
 */
public abstract class ATreeNode implements ITreeNode {

    @Getter
    @Setter
    private ClusterModel ctx;

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
    public List<ITreeNode> getChildElements() {
        return null;
    }

    @Override
    public LeafState getLeafState() {
        return null;
    }

    @Override
    public JBPopupMenu getMenu(Project project) {
        return null;
    }

}
