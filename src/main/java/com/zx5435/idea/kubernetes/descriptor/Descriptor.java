package com.zx5435.idea.kubernetes.descriptor;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.ide.util.treeView.PresentableNodeDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author 913332
 */
public class Descriptor<T> extends PresentableNodeDescriptor<T> {

    protected T element;

    public Descriptor(T e, @Nullable NodeDescriptor parentDescriptor) {
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
