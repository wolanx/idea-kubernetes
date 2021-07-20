package com.zx5435.idea.kubernetes.editor;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class YamlEditorView {

    public static void load(Project project, String name, String content) {
        FileEditorManager fm = FileEditorManager.getInstance(project);

        Path path = Paths.get(FileUtil.getTempDirectory(), "idea-kubernetes", name + ".yaml");
        try {
            FileUtils.write(path.toFile(), content, StandardCharsets.UTF_8, false);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        VirtualFileManager vm = VirtualFileManager.getInstance();
        VirtualFile vf = vm.findFileByNioPath(path);

        if (vf != null) {
            fm.openTextEditor(new OpenFileDescriptor(project, vf), true);
        }
    }
}
