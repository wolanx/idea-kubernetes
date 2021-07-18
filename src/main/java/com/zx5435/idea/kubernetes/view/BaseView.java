package com.zx5435.idea.kubernetes.view;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.ide.util.treeView.PresentableNodeDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author zx5435
 */
public class BaseView<T> extends PresentableNodeDescriptor<T> {

    protected T element;

    public BaseView(T e, @Nullable NodeDescriptor parentDescriptor) {
        super(null, parentDescriptor);
        element = e;
    }

    @Override
    protected void update(@NotNull PresentationData presentation) {
        //
    }

    @Override
    public T getElement() {
        return element;
    }

}
