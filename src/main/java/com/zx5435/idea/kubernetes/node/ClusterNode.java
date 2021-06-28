package com.zx5435.idea.kubernetes.node;

import com.intellij.icons.AllIcons;
import com.zx5435.idea.kubernetes.model.Cluster;
import com.zx5435.idea.kubernetes.model.IResModel;
import com.zx5435.idea.kubernetes.other.ClusterEditDialog;
import io.fabric8.kubernetes.api.model.Namespace;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 913332
 */
@Slf4j
public class ClusterNode extends ATreeNode {

    @Getter
    private final List<ITreeNode> childElements = new ArrayList<>();

    public ClusterNode(String label, IResModel model) {
        setLabel(label);
        setModel(model);
        this.setCtx(new Cluster(label, getModel()));

        childElements.add(new FolderNode("Namespaces", getModel(), Namespace.class));
        childElements.add(new FolderNode.WorkloadsNode());
        childElements.add(new FolderNode.NetworksNode());
        childElements.add(new FolderNode.ConfigurationsNode());
        childElements.add(new FolderNode.VolumesNode());
    }

    @Override
    public JPopupMenu getMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem b1 = new JMenuItem("Edit", AllIcons.Actions.Edit);
        b1.addActionListener(e -> {
            doEdit();
        });
        menu.add(b1);

        JMenuItem b2 = new JMenuItem("Delete", AllIcons.Actions.Close);
        b2.addActionListener(e -> {
            System.out.println("delete");
        });
        menu.add(b2);

        return menu;
    }

    private void doEdit() {
        new ClusterEditDialog().show();
    }

}
