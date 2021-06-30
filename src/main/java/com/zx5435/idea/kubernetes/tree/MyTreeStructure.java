package com.zx5435.idea.kubernetes.tree;

import com.intellij.ide.util.treeView.AbstractTreeStructure;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.ui.tree.LeafState;
import com.zx5435.idea.kubernetes.descriptor.Descriptor;
import com.zx5435.idea.kubernetes.descriptor.FolderDescriptor;
import com.zx5435.idea.kubernetes.descriptor.ResourceDescriptor;
import com.zx5435.idea.kubernetes.model.IResModel;
import com.zx5435.idea.kubernetes.node.FolderNode;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * @author 913332
 */
public class MyTreeStructure extends AbstractTreeStructure {

    private final IResModel model;

    public MyTreeStructure(IResModel model) {
        this.model = model;
    }

    @Override
    public @NotNull IResModel getRootElement() {
        return model;
    }

    @Override
    public Object @NotNull [] getChildElements(@NotNull Object element) {
        if (element == model) {
            return model.getClusters().toArray();
        } else {
            return getValidContributions((ITreeNode) element).toArray();
        }
    }

    @Override
    public @Nullable Object getParentElement(@NotNull Object element) {
        return model;
    }

    @Override
    public @NotNull NodeDescriptor<?> createDescriptor(@NotNull Object element, @Nullable NodeDescriptor parent) {
        // fixme map createDescriptor
        if (element instanceof FolderNode) {
            return new FolderDescriptor<>((FolderNode) element, parent);
        } else if (element instanceof ITreeNode) {
            return new ResourceDescriptor<>((ITreeNode) element, parent);
        } else {
            return new Descriptor<>(element, parent);
        }
    }

    @Override
    public boolean isToBuildChildrenInBackground(@NotNull Object element) {
        return true;
    }

    @Override
    public void commit() {

    }

    @Override
    public boolean hasSomethingToCommit() {
        return false;
    }

    @Override
    public @NotNull LeafState getLeafState(@NotNull Object element) {
        if (element instanceof ITreeNode) {
            LeafState leafState = ((ITreeNode) element).getLeafState();
            if (leafState != null) {
                return leafState;
            }
        }
        return super.getLeafState(element);
    }

    // fixme better
    // todo 子节点 合并后，不请求
    public List<ITreeNode> getValidContributions(ITreeNode node) {
        List<ITreeNode> ret = getTreeNodes(node);
        ret.forEach(v -> {
            v.setCtx(node.getCtx());
            v.setModel(model);
//            System.out.println(v.getLabel() + " " + Integer.toHexString(v.hashCode()));
        });
        return ret;
    }

    private List<ITreeNode> getTreeNodes(ITreeNode node) {
        if (node instanceof FolderNode) {
            Class<?> kind = ((FolderNode) node).getKind();
            if (kind != null) {
                return model.getResByKind(node, kind);
            }
        }

        List<ITreeNode> ret = node.getChildElements();

        if (ret != null) {
            return ret;
        } else {
            return Collections.emptyList();
        }
    }

}
