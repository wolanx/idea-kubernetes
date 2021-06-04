package com.zx5435.idea.kubernetes.tree;

import com.intellij.ide.util.treeView.AbstractTreeStructure;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.ui.tree.LeafState;
import com.zx5435.idea.kubernetes.tree.descriptor.Descriptor;
import com.zx5435.idea.kubernetes.tree.descriptor.FolderDescriptor;
import com.zx5435.idea.kubernetes.tree.descriptor.ResourceDescriptor;
import com.zx5435.idea.kubernetes.tree.model.ResModel;
import com.zx5435.idea.kubernetes.tree.node.FolderNode;
import com.zx5435.idea.kubernetes.tree.node.ITreeNode;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.fabric8.kubernetes.api.model.batch.v1.CronJob;
import io.fabric8.kubernetes.api.model.batch.v1.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 913332
 */
public class MyTreeStructure extends AbstractTreeStructure {

    private final ResModel model;

    public MyTreeStructure(ResModel model) {
        this.model = model;
    }

    @Override
    public @NotNull ResModel getRootElement() {
        return model;
    }

    @Override
    public Object @NotNull [] getChildElements(@NotNull Object element) {
        if (element == model) {
            return model.getAllContexts().toArray();
        } else {
            return getValidContributions(element).toArray();
        }
    }

    @Override
    public @Nullable Object getParentElement(@NotNull Object element) {
        return null;
    }

    @Override
    public @NotNull NodeDescriptor<?> createDescriptor(@NotNull Object element, @Nullable NodeDescriptor parent) {
        if (element instanceof FolderNode) {
            return new FolderDescriptor<>((FolderNode) element, parent);
        } else if (element instanceof ITreeNode) {
            return new ResourceDescriptor<>((ITreeNode) element, parent);
        } else {
            return new Descriptor<>(element, parent);
        }
    }

    @Override
    public void commit() {

    }

    @Override
    public boolean hasSomethingToCommit() {
        return false;
    }

    @Override
    public @NotNull LeafState getLeafState(@NotNull Object element) {
        if (element instanceof ITreeNode) {
            LeafState leafState = ((ITreeNode) element).getLeafState();
            if (leafState != null) {
                return leafState;
            }
        }
        return super.getLeafState(element);
    }

    //

    public List<?> getValidContributions(Object e) {
        if (e instanceof FolderNode) {
            Class<?> kind = ((FolderNode) e).getKind();
            if (kind != null) {
                return model.getResByKind(kind);
            }
        }

        String name = e.getClass().getSimpleName();
        List<ITreeNode> ret = new ArrayList<>();
        switch (name) {
            case "ContextNode":
                ret.add(new FolderNode("Namespaces", Namespace.class));
                ret.add(new FolderNode.WorkloadsNode());
                ret.add(new FolderNode.NetworksNode());
                ret.add(new FolderNode.ConfigurationsNode());
                ret.add(new FolderNode.VolumesNode());
                return ret;
            case "WorkloadsNode":
                ret.add(new FolderNode("Deployments", Deployment.class));
                ret.add(new FolderNode("StatefulSets", StatefulSet.class));
                ret.add(new FolderNode("DaemonSets", DaemonSet.class));
                ret.add(new FolderNode("Jobs", Job.class));
                ret.add(new FolderNode("CronJobs", CronJob.class));
                ret.add(new FolderNode("Pods", Pod.class));
                ret.add(new FolderNode("Custom Resources"));
                return ret;
            case "NetworksNode":
                ret.add(new FolderNode("Services"));
                ret.add(new FolderNode("Ingresses"));
                return ret;
            case "ConfigurationsNode":
                ret.add(new FolderNode("ConfigMap"));
                ret.add(new FolderNode("Secrets"));
                return ret;
            case "VolumesNode":
                ret.add(new FolderNode("Persistent Volume Claims"));
                ret.add(new FolderNode("Persistent Volumes"));
                ret.add(new FolderNode("StorageClasses"));
                return ret;
            default:
                return Collections.emptyList();
        }
    }

}
