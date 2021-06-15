package com.zx5435.idea.kubernetes.tree;

import com.intellij.ui.tree.StructureTreeModel;
import com.zx5435.idea.kubernetes.model.IResModel;
import com.zx5435.idea.kubernetes.model.ITreeObserver;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.tree.TreePath;
import java.util.List;

/**
 * @author 913332
 */
@Slf4j
public class TreeObserver implements ITreeObserver {

    @Setter
    private StructureTreeModel<MyTreeStructure> treeModel;

    @Setter
    private MyTreeStructure structure;

    public TreeObserver(StructureTreeModel<MyTreeStructure> treeModel, MyTreeStructure structure, IResModel model) {
        setTreeModel(treeModel);
        setStructure(structure);
        model.addListener(this);
    }

    @Override
    public void added() {
        log.warn("adder");
    }

    @Override
    public void removed() {
        log.warn("removed");
    }

    @Override
    public void modified(ITreeNode node) {
        List<ITreeNode> nodePath = node.getPath();
        if (nodePath == null) {
            treeModel.invalidate();
        } else {
            TreePath path = new TreePath(nodePath);
            log.warn("modified");
            treeModel.invalidate(path, true);
        }
    }

}
