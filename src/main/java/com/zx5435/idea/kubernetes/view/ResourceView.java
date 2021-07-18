package com.zx5435.idea.kubernetes.view;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author zx5435
 */
public class ResourceView<T extends ITreeNode> extends BaseView<T> {

    public ResourceView(T e, @Nullable NodeDescriptor parentDescriptor) {
        super(e, parentDescriptor);
    }

    @Override
    protected void update(@NotNull PresentationData presentation) {
        super.update(presentation);
        presentation.setPresentableText(getElement().getLabel());
        presentation.setIcon(getElement().getIcon());
    }

}
