## doc

https://plugins.jetbrains.com/docs/intellij/tool-windows.html
storage https://plugins.jetbrains.com/docs/intellij/persisting-state-of-components.html?from=jetbrains.org#using-persistentstatecomponent
tool-windows https://www.jetbrains.com/help/idea/tool-windows.html#general-tool-windows-layout

#

- gradle https://www.jetbrains.com/help/idea/gradle.html
- icons https://jetbrains.design/intellij/resources/icons_list/
- ui guide https://jetbrains.design/intellij/controls/text_area/

# k8s fabric8 api

https://github.com/fabric8io/kubernetes-client/blob/master/doc/CHEATSHEET.md

# demo

- （二十四）IntelliJ 插件开发——Idea下方工具窗口 https://www.jianshu.com/p/c66c38b7dd4d

- 记事本demo https://juejin.cn/post/6844904127990857742

# cmd

k get pod | grep blog- | awk '{print $1}' | xargs k delete pod

# other

// 格式化代码 CodeStyleManager.getInstance(project).reformat(psiClass); // 用编辑器打开指定文件 FileEditorManager.getInstance(project)
.openTextEditor(new OpenFileDescriptor(project, virtualFile), true);

