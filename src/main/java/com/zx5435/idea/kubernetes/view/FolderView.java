package com.zx5435.idea.kubernetes.view;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.zx5435.idea.kubernetes.node.FolderNode;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author zx5435
 */
@Slf4j
public class FolderView<T extends FolderNode> extends BaseView<T> {

    public FolderView(T e, @Nullable NodeDescriptor parentDescriptor) {
        super(e, parentDescriptor);
    }

    @Override
    protected void update(@NotNull PresentationData presentation) {
        super.update(presentation);
        presentation.setPresentableText(getElement().getLabel());
    }

    public void watchUpdate() {
        if (getElement().getKind() != null) {
            log.warn("watch start");
        }
    }

    public void stopWatchUpdate() {
        log.warn("watch end");
    }

}
