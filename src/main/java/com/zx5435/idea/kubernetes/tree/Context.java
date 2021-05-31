package com.zx5435.idea.kubernetes.tree;

import lombok.Getter;

/**
 * @author 913332
 */
public class Context {

    @Getter
    private String name;

    public Context(String name) {
        this.name = name;
    }

    public String getLabel() {
        return this.name;
    }

}
