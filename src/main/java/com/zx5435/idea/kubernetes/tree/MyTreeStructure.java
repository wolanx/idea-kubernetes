package com.zx5435.idea.kubernetes.tree;

import com.intellij.ide.util.treeView.AbstractTreeStructure;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.zx5435.idea.kubernetes.tree.descriptor.Descriptor;
import com.zx5435.idea.kubernetes.tree.descriptor.ResourceDescriptor;
import com.zx5435.idea.kubernetes.tree.node.ContextNode;
import com.zx5435.idea.kubernetes.tree.node.FolderNode;
import com.zx5435.idea.kubernetes.tree.node.ITreeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
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
    public @NotNull ResourceModel getRootElement() {
        return model;
    }

    @Override
    public Object @NotNull [] getChildElements(@NotNull Object element) {
        if (element == model) {
            return model.getAllContexts().toArray();
        } else {
            return getValidContributions(element).toArray();
        }
    }

    @Override
    public @Nullable Object getParentElement(@NotNull Object element) {
        return null;
    }

    @Override
    public @NotNull NodeDescriptor<?> createDescriptor(@NotNull Object element, @Nullable NodeDescriptor parent) {
        if (element instanceof ITreeNode) {
            return new ResourceDescriptor<>((ITreeNode) element, parent);
        } else {
            return new Descriptor<>(element, parent);
        }
    }

    @Override
    public void commit() {

    }

    @Override
    public boolean hasSomethingToCommit() {
        return false;
    }

    //

    public List<ITreeNode> getValidContributions(Object e) {
        if (!(e instanceof ITreeNode)) {
            return Collections.emptyList();
        }
        String name = ((ITreeNode) e).getClass().getSimpleName();
        switch (name){
            case "ContextNode":
                ArrayList<ITreeNode> ret = new ArrayList<>();
                ret.add(new FolderNode("aa"));
                ret.add(new FolderNode("bb"));
                ret.add(new FolderNode("cc"));
                return ret;
            default:
                return Collections.emptyList();
        }
    }

}
