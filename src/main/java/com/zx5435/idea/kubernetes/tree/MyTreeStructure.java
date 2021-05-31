package com.zx5435.idea.kubernetes.tree;

import com.intellij.ide.util.treeView.AbstractTreeStructure;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.zx5435.idea.kubernetes.dom.res.ClusterNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 913332
 */
public class MyTreeStructure extends AbstractTreeStructure {

    private final ResourceModel model;

    public MyTreeStructure(ResourceModel model) {
        this.model = model;
    }

    @Override
    public @NotNull Object getRootElement() {
        return model;
    }

    @Override
    public Object @NotNull [] getChildElements(@NotNull Object element) {
        if (element == model) {
            return model.getAllContexts().toArray();
        }else{
            return model.getAllContexts().toArray();
        }
    }

    @Override
    public @Nullable Object getParentElement(@NotNull Object element) {
        return null;
    }

    @Override
    public TestNodeDescriptor createDescriptor(@NotNull Object element, @Nullable NodeDescriptor parentDescriptor) {
//        return new TestNodeDescriptor(new ClusterNode("sad"),element);
        return new TestNodeDescriptor(element, parentDescriptor);
    }

    @Override
    public void commit() {

    }

    @Override
    public boolean hasSomethingToCommit() {
        return false;
    }
}
