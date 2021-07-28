package com.zx5435.idea.kubernetes.node;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.JBMenuItem;
import com.intellij.openapi.ui.JBPopupMenu;
import com.intellij.ui.tree.LeafState;
import com.zx5435.idea.kubernetes.editor.TableEditorView;
import com.zx5435.idea.kubernetes.model.IResModel;
import com.zx5435.idea.kubernetes.utils.KubeUtil;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.fabric8.kubernetes.api.model.batch.v1.CronJob;
import io.fabric8.kubernetes.api.model.batch.v1.Job;
import io.fabric8.kubernetes.api.model.storage.StorageClass;
import io.fabric8.openshift.api.model.Ingress;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zx5435
 */
@Slf4j
public class FolderNode extends ATreeNode {

    public static class WorkloadsNode extends FolderNode {

        @Getter
        private final List<ITreeNode> childElements = new ArrayList<>();

        public WorkloadsNode() {
            super("Workloads");
            childElements.add(new FolderNode("Deployments", getModel(), Deployment.class));
            childElements.add(new FolderNode("StatefulSets", getModel(), StatefulSet.class));
            childElements.add(new FolderNode("DaemonSets", getModel(), DaemonSet.class));
            childElements.add(new FolderNode("Jobs", getModel(), Job.class));
            childElements.add(new FolderNode("CronJobs", getModel(), CronJob.class));
            childElements.add(new FolderNode("Pods", getModel(), Pod.class));
            childElements.add(new FolderNode("Custom Resources", getModel(), CustomResourceDefinition.class));
        }

    }

    public static class NetworksNode extends FolderNode {

        @Getter
        private final List<ITreeNode> childElements = new ArrayList<>();

        public NetworksNode() {
            super("Networks");
            childElements.add(new FolderNode("Services", getModel(), Service.class));
            childElements.add(new FolderNode("Ingresses", getModel(), Ingress.class));
        }

    }

    public static class ConfigurationsNode extends FolderNode {

        @Getter
        private final List<ITreeNode> childElements = new ArrayList<>();

        public ConfigurationsNode() {
            super("Configurations");
            childElements.add(new FolderNode("ConfigMap", getModel(), ConfigMap.class));
            childElements.add(new FolderNode("Secrets", getModel(), Secret.class));
        }

    }

    public static class VolumesNode extends FolderNode {

        @Getter
        private final List<ITreeNode> childElements = new ArrayList<>();

        public VolumesNode() {
            super("Volumes");
            childElements.add(new FolderNode("Persistent Volume Claims", getModel(), PersistentVolumeClaim.class));
            childElements.add(new FolderNode("Persistent Volumes", getModel(), PersistentVolume.class));
            childElements.add(new FolderNode("StorageClasses", getModel(), StorageClass.class));
        }

    }

    ////////

    @Getter
    @Setter
    private Class<?> kind;

    public FolderNode(String label) {
        setLabel(label);
    }

    public FolderNode(String label, IResModel model, Class<?> kind) {
        setLabel(label);
        setModel(model);
        setKind(kind);
    }

    @Override
    public LeafState getLeafState() {
        return LeafState.NEVER;
    }

    @Override
    public JBPopupMenu getMenu(Project project) {
        if (kind != null) {
            JBPopupMenu menu = new JBPopupMenu();

            JBMenuItem b1 = new JBMenuItem("Refresh", AllIcons.Actions.Refresh);
            b1.addActionListener(e -> {
                getModel().reloadByKind(this, kind);
            });
            menu.add(b1);

            JBMenuItem b2 = new JBMenuItem("Load", AllIcons.Nodes.DataTables);
            b2.addActionListener(e -> {
                String content = KubeUtil.getTable(getCtx(), kind);
                TableEditorView.load(project, getCtx().getName() + "_" + getLabel().toLowerCase(), content);
            });
            menu.add(b2);

            return menu;
        }
        return super.getMenu(project);
    }

}
