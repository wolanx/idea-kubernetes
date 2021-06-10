package com.zx5435.idea.kubernetes.descriptor;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.zx5435.idea.kubernetes.node.FolderNode;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author 913332
 */
@Slf4j
public class FolderDescriptor<T extends FolderNode> extends Descriptor<T> {

    public FolderDescriptor(T e, @Nullable NodeDescriptor parentDescriptor) {
        super(e, parentDescriptor);
    }

    @Override
    protected void update(@NotNull PresentationData presentation) {
        super.update(presentation);
        presentation.setPresentableText(getElement().getLabel());
    }

    public void watchUpdate() {
        if (getElement().getKind() != null) {
            log.warn("watchUpdate");
        }
    }

    public void stopWatchUpdate() {
        log.warn("stopWatchUpdate");
    }

}
