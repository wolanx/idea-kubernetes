package com.zx5435.idea.kubernetes.tree.descriptor;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.zx5435.idea.kubernetes.tree.node.ITreeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author 913332
 */
public class FolderDescriptor<T extends ITreeNode> extends Descriptor<T> {

    public FolderDescriptor(T e, @Nullable NodeDescriptor parentDescriptor) {
        super(e, parentDescriptor);
        System.out.println("e.getLabel() = " + e.getLabel());
    }

    @Override
    protected void update(@NotNull PresentationData presentation) {
        super.update(presentation);
        presentation.setPresentableText(getElement().getLabel());
    }

}
