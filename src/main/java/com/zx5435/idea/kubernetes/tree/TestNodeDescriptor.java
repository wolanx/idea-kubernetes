package com.zx5435.idea.kubernetes.tree;

import com.intellij.ide.util.treeView.NodeDescriptor;
import org.jetbrains.annotations.Nullable;

/**
 * @author 913332
 */
public class TestNodeDescriptor<T> extends NodeDescriptor<T> {

    private final T element;

    public TestNodeDescriptor(T e, @Nullable NodeDescriptor parentDescriptor) {
        super(null, parentDescriptor);
        element = e;
        myName = e.toString();
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public T getElement() {
        return element;
    }



}
