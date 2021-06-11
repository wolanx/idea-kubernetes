package com.zx5435.idea.kubernetes.tree;

import com.intellij.ide.util.treeView.AbstractTreeStructure;
import com.intellij.ui.tree.StructureTreeModel;
import com.zx5435.idea.kubernetes.model.IResModel;
import com.zx5435.idea.kubernetes.model.ITreeObserver;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 913332
 */
@Slf4j
public class TreeObserver implements ITreeObserver {

    @Setter
    private StructureTreeModel<AbstractTreeStructure> treeModel;

    public TreeObserver(StructureTreeModel<AbstractTreeStructure> treeModel, MyTreeStructure structure, IResModel model) {
        setTreeModel(treeModel);
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
        treeModel.invalidate();
        log.warn("modified");
    }

    // invalidate

}
