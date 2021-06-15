package com.zx5435.idea.kubernetes.tree;

import com.intellij.ide.util.treeView.AbstractTreeStructure;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.ui.tree.LeafState;
import com.zx5435.idea.kubernetes.descriptor.Descriptor;
import com.zx5435.idea.kubernetes.descriptor.FolderDescriptor;
import com.zx5435.idea.kubernetes.descriptor.ResourceDescriptor;
import com.zx5435.idea.kubernetes.model.IResModel;
import com.zx5435.idea.kubernetes.node.FolderNode;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.fabric8.kubernetes.api.model.batch.v1.CronJob;
import io.fabric8.kubernetes.api.model.batch.v1.Job;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 913332
 */
public class MyTreeStructure extends AbstractTreeStructure {

    @Getter
    @Setter
    private IResModel model;

    public MyTreeStructure(IResModel model) {
        this.model = model;
    }

    @Override
    public @NotNull IResModel getRootElement() {
        return model;
    }

    @Override
    public Object @NotNull [] getChildElements(@NotNull Object element) {
        if (element == model) {
            return model.getAllContexts().toArray();
        } else {
            return getValidContributions((ITreeNode) element).toArray();
        }
    }

    @Override
    public @Nullable Object getParentElement(@NotNull Object element) {
        return null;
    }

    @Override
    public @NotNull NodeDescriptor<?> createDescriptor(@NotNull Object element, @Nullable NodeDescriptor parent) {
        // todo map createDescriptor
        if (element instanceof FolderNode) {
            return new FolderDescriptor<>((FolderNode) element, parent);
        } else if (element instanceof ITreeNode) {
            return new ResourceDescriptor<>((ITreeNode) element, parent);
        } else {
            return new Descriptor<>(element, parent);
        }
    }

    @Override
    public boolean isToBuildChildrenInBackground(@NotNull Object element) {
        return true;
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

    public List<ITreeNode> getValidContributions(ITreeNode node) {
        if (node instanceof FolderNode) {
            Class<?> kind = ((FolderNode) node).getKind();
            if (kind != null) {
                return model.getResByKind(kind);
            }
        }

        String name = node.getClass().getSimpleName();
        List<ITreeNode> ret = new ArrayList<>();
        switch (name) {
            case "ContextNode":
                ret.add(new FolderNode("Namespaces", node, model, Namespace.class));
                ret.add(new FolderNode.WorkloadsNode(node));
                ret.add(new FolderNode.NetworksNode(node));
                ret.add(new FolderNode.ConfigurationsNode(node));
                ret.add(new FolderNode.VolumesNode(node));
                return ret;
            case "WorkloadsNode":
                ret.add(new FolderNode("Deployments", node, model, Deployment.class));
                ret.add(new FolderNode("StatefulSets", node, model, StatefulSet.class));
                ret.add(new FolderNode("DaemonSets", node, model, DaemonSet.class));
                ret.add(new FolderNode("Jobs", node, model, Job.class));
                ret.add(new FolderNode("CronJobs", node, model, CronJob.class));
                ret.add(new FolderNode("Pods", node, model, Pod.class));
                ret.add(new FolderNode("Custom Resources", node));
                return ret;
            case "NetworksNode":
                ret.add(new FolderNode("Services", node));
                ret.add(new FolderNode("Ingresses", node));
                return ret;
            case "ConfigurationsNode":
                ret.add(new FolderNode("ConfigMap", node, model, ConfigMap.class));
                ret.add(new FolderNode("Secrets", node));
                return ret;
            case "VolumesNode":
                ret.add(new FolderNode("Persistent Volume Claims", node));
                ret.add(new FolderNode("Persistent Volumes", node));
                ret.add(new FolderNode("StorageClasses", node));
                return ret;
            default:
                return Collections.emptyList();
        }
    }

}
