package com.zx5435.idea.kubernetes.model;

import com.intellij.ui.treeStructure.Tree;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import com.zx5435.idea.kubernetes.tree.MyTree;
import io.fabric8.kubernetes.api.model.Pod;

import java.util.List;

/**
 * @author 913332
 */
public class TestModel {

    public static void main(String[] args) {
        ResModelImpl res = new ResModelImpl();
//        List<ITreeNode> ret = res.getResByKind(Pod.class);
//        for (ITreeNode one : ret) {
//            System.out.println(one.getLabel());
//        }

//        res.listPodWatch();
//        Tree tree = MyTree.bindAction(null);
    }

}
