package com.zx5435.idea.kubernetes.tree;

import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.ui.tree.StructureTreeModel;
import com.zx5435.idea.kubernetes.model.IResModel;
import com.zx5435.idea.kubernetes.model.ITreeObserver;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.Enumeration;

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
        log.warn("modified");
        treeModel.getInvoker().invoke(() -> {
            //TreePath path = new TreePath(nodePath.get(0));
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();

            TreePath path = findTreePath(node, root);
            if (path == null) {
                treeModel.invalidate();
            } else {
                treeModel.invalidate(path, true);
            }
        });
    }

    private TreePath findTreePath(ITreeNode node, DefaultMutableTreeNode prev) {
        Enumeration<TreeNode> child = prev.children();
        while (child.hasMoreElements()) {
            DefaultMutableTreeNode next = (DefaultMutableTreeNode) child.nextElement();
            if (((NodeDescriptor<?>) next.getUserObject()).getElement() == node) {
                return new TreePath(next.getPath());
            }
            TreePath path = findTreePath(node, next);
            if (path != null) {
                return path;
            }
        }
        return null;
    }

}
