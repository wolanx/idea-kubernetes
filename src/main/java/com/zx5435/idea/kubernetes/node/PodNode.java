package com.zx5435.idea.kubernetes.node;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.zx5435.idea.kubernetes.service.KubeUtil;
import io.fabric8.kubernetes.api.model.Pod;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author 913332
 */
@Slf4j
public class PodNode extends ATreeNode {

    Pod ins;

    public PodNode(Pod ns) {
        this.ins = ns;
    }

    @Override
    public String getLabel() {
        return ins.getMetadata().getName();
    }

    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/img/pod-running.svg", PodNode.class);
    }

    @SneakyThrows
    @Override
    public JPopupMenu getMenu(Project project) {
        Path path = Paths.get(FileUtil.getTempDirectory(), "idea-kubernetes", "a.txt");
        JPopupMenu menu = new JPopupMenu();

        FileEditorManager fIns = FileEditorManager.getInstance(project);

        JMenuItem b1 = new JMenuItem("Load", AllIcons.Actions.Show);
        b1.addActionListener(e -> {
            VirtualFileManager vm = VirtualFileManager.getInstance();

            String yaml = KubeUtil.getPod(getCtx().getClient(), "zx5435", getLabel());
            try {
                FileUtils.write(path.toFile(), yaml, StandardCharsets.UTF_8, false);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            VirtualFile fFile = vm.findFileByNioPath(path);

            if (fFile != null) {
                fIns.openFile(fFile, true, true);
            }
        });
        menu.add(b1);

        JMenuItem b2 = new JMenuItem("Delete", AllIcons.Actions.Close);
        b2.addActionListener(e -> {
            log.warn("delete");
            System.out.println("getModel().toString() = " + getModel().toString());
        });
        menu.add(b2);

        return menu;
    }

}
