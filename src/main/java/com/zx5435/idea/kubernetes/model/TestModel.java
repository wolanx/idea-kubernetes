package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.ITreeNode;
import io.fabric8.kubernetes.api.model.ConfigMap;

import java.util.List;

/**
 * @author 913332
 */
public class TestModel {

    public static void main(String[] args) {
        ResModelImpl res = new ResModelImpl();
        List<ITreeNode> ret = res.getResByKind(ConfigMap.class);

        System.out.println(ret);
    }

}
