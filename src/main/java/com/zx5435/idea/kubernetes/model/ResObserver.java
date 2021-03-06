package com.zx5435.idea.kubernetes.model;

import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.ui.tree.StructureTreeModel;
import com.zx5435.idea.kubernetes.model.IResModel;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import com.zx5435.idea.kubernetes.tree.MyTreeStructure;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.Enumeration;

/**
 * @author zx5435
 */
@Slf4j
public class ResObserver {

    @Setter
    private StructureTreeModel<MyTreeStructure> treeModel;

    @Setter
    private MyTreeStructure structure;

    public ResObserver(StructureTreeModel<MyTreeStructure> treeModel, MyTreeStructure structure, IResModel model) {
        setTreeModel(treeModel);
        setStructure(structure);
        model.addListener(this);
    }

    public void useNs(String ns) {
        System.out.println("ResObserver.useNs " + ns);
        treeModel.invalidate(); // todo  还不能加
    }

    public void added() {
        log.warn("adder");
    }

    public void removed() {
        log.warn("removed");
    }

    public void nodeChange(ITreeNode node) {
        System.out.println("ResObserver.nodeChange");
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
