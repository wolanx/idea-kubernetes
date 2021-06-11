package com.zx5435.idea.kubernetes.tree;

import com.intellij.ide.util.treeView.AbstractTreeStructure;
import com.intellij.ui.tree.StructureTreeModel;
import com.zx5435.idea.kubernetes.model.IResUpdateListener;
import com.zx5435.idea.kubernetes.model.IResModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 913332
 */
@Slf4j
public class MyTreeUpdater implements IResUpdateListener {

    public MyTreeUpdater(StructureTreeModel<AbstractTreeStructure> treeModel, MyTreeStructure structure, IResModel model) {
        model.addListener(this);
    }

    @Override
    public void added() {
        log.warn("adder");
    }

    @Override
    public void removed() {
        log.warn("removed");
    }

    @Override
    public void modified() {
        log.warn("modified");
    }

}
