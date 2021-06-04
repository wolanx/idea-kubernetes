package com.zx5435.idea.kubernetes.descriptor;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author 913332
 */
public class ResourceDescriptor<T extends ITreeNode> extends Descriptor<T> {

    public ResourceDescriptor(T e, @Nullable NodeDescriptor parentDescriptor) {
        super(e, parentDescriptor);
    }

    @Override
    protected void update(@NotNull PresentationData presentation) {
        super.update(presentation);
        presentation.setPresentableText(getElement().getLabel());
        presentation.setIcon(getElement().getIcon());
    }

}
