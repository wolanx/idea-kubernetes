package com.zx5435.idea.kubernetes.tree.node;

/**
 * @author 913332
 */
public class FolderNode implements ITreeNode {

    private String name;

    public FolderNode(String name) {
        this.name = name;
    }

    @Override
    public String getLabel() {
        return name;
    }

}
