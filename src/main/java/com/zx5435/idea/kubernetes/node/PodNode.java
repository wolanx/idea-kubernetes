package com.zx5435.idea.kubernetes.node;

import com.intellij.icons.AllIcons;
import com.intellij.ide.highlighter.HtmlFileType;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.EditorKind;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import io.fabric8.kubernetes.api.model.Pod;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

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

    @Override
    public JPopupMenu getMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem b1 = new JMenuItem("Load", AllIcons.Actions.Show);
        b1.addActionListener(e -> {
            Project project = ProjectManager.getInstance().getDefaultProject();
            VirtualFileManager vm = VirtualFileManager.getInstance();
            VirtualFile zxc = vm.findFileById(123);
//            VirtualFile v = new VirtualFile() {
            EditorFactory edIns = EditorFactory.getInstance();
            Document doc = edIns.createDocument("hehe");
            doc.setReadOnly(true);
            PsiFile f = PsiFileFactory.getInstance(project).createFileFromText(Language.ANY, "heheh");
            VirtualFile virtualFile = f.getVirtualFile();
            Editor editor = edIns.createEditor(doc, project, FileTypes.PLAIN_TEXT, true);
            FileEditorManager.getInstance(project).openTextEditor(new OpenFileDescriptor(project, virtualFile), true);
//            edIns.refreshAllEditors();
//            EditorFactory.getInstance().refreshAllEditors();
//            FileViewProvider viewProvider = f.getViewProvider();
//            EditorFactory.getInstance().releaseEditor(viewProvider);
//            FileDocumentManager.getInstance().reloadFromDisk(doc);
            PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
            Editor[] allEditors = edIns.getAllEditors();
            System.out.println("hehe " + allEditors.length);
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
