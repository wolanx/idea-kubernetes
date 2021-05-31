package com.zx5435.idea.kubernetes.tree;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.ide.util.treeView.PresentableNodeDescriptor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Descriptor<T> extends PresentableNodeDescriptor<T> {
    protected Descriptor(Project project, @Nullable NodeDescriptor parentDescriptor) {
        super(project, parentDescriptor);
    }

//    public Descriptor(T e) {
//        super(null, @Nullable NodeDescriptor parentDescriptor);
//    }

    @Override
    protected void update(@NotNull PresentationData presentation) {

    }

    @Override
    public T getElement() {
        return null;
    }
}
