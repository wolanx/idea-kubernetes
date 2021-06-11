package com.zx5435.idea.kubernetes.tree;

import com.intellij.ide.util.treeView.AbstractTreeStructure;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.ui.tree.AsyncTreeModel;
import com.intellij.ui.tree.StructureTreeModel;
import com.intellij.ui.treeStructure.Tree;
import com.zx5435.idea.kubernetes.descriptor.Descriptor;
import com.zx5435.idea.kubernetes.descriptor.FolderDescriptor;
import com.zx5435.idea.kubernetes.model.IResModel;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 913332
 */
@Slf4j
public class MyTree {

    @SneakyThrows
    public static Tree getIns(Project project) {
        IResModel resModel = ServiceManager.getService(IResModel.class);
        MyTreeStructure structure = new MyTreeStructure(resModel);

        StructureTreeModel<AbstractTreeStructure> treeModel = StructureTreeModel.class
                .getConstructor(AbstractTreeStructure.class, Disposable.class)
                .newInstance(structure, project);

        new MyTreeUpdater(treeModel, structure, resModel);

        Tree tree = new Tree(new AsyncTreeModel(treeModel, project));

        tree.setRootVisible(false);

        tree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent e) {
                Object o = ((DefaultMutableTreeNode) e.getPath().getLastPathComponent()).getUserObject();
                if (o instanceof FolderDescriptor) {
                    ((FolderDescriptor<?>) o).watchUpdate();
                }
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent e) {
                Object o = ((DefaultMutableTreeNode) e.getPath().getLastPathComponent()).getUserObject();
                if (o instanceof FolderDescriptor) {
                    ((FolderDescriptor<?>) o).stopWatchUpdate();
                }
            }
        });

        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    myPopupEvent(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    myPopupEvent(e);
                }
            }

            void myPopupEvent(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                JTree tree = (JTree) e.getSource();
                TreePath path = tree.getClosestPathForLocation(x, y);
                if (path == null) {
                    return;
                }
                Object obj = ((DefaultMutableTreeNode) path.getLastPathComponent()).getUserObject();
                if (obj instanceof Descriptor) {
                    JPopupMenu menu = ((ITreeNode) ((Descriptor<?>) obj).getElement()).getMenu();
                    if (menu != null) {
                        menu.show(tree, x, y);
                    }
                }
            }
        });

        return tree;
    }

}
