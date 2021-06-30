package com.zx5435.idea.kubernetes.node;

import com.intellij.ui.tree.LeafState;
import com.zx5435.idea.kubernetes.model.ClusterModel;
import com.zx5435.idea.kubernetes.model.IResModel;

import javax.swing.*;
import java.util.List;

/**
 * @author 913332
 */
public interface ITreeNode {

    void setCtx(ClusterModel ctx);

    void setModel(IResModel model);

    ClusterModel getCtx();

    String getLabel();

    Icon getIcon();

    List<ITreeNode> getChildElements();

    LeafState getLeafState();

    JPopupMenu getMenu();

}
