package com.zx5435.idea.kubernetes.service;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.zx5435.idea.kubernetes.model.IResModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Z:\www\zx5435\idea-kubernetes\build\idea-sandbox\config\options
 */
@State(name = "KubeStorage", storages = {@Storage(value = "zx5435.k8s.plugin.xml")})
@Slf4j
public class KubeStorage implements PersistentStateComponent<KubeStorage> {

    @Getter
    public List<KubeConfig> kubeConfigs;

    @Override
    public void loadState(@NotNull KubeStorage state) {
        XmlSerializerUtil.copyBean(state, this);
        System.out.println("loadState");
    }

    @Override
    public void initializeComponent() {
        if (kubeConfigs == null) {
            kubeConfigs = new ArrayList<>();
        }
        System.out.println("initializeComponent");
    }

    @Nullable
    @Override
    public KubeStorage getState() {
        return this;
    }

    public void createByNameAndContent(String name, String content) {
        System.out.println(name + content.length());
        kubeConfigs.add(KubeConfig.of(name, content));
        ServiceManager.getService(IResModel.class).fireCluster();
    }

    public void updateByNameAndContent(String namePrev, String nameNext, String content) {
        System.out.printf("update %s=>%s %d\n", namePrev, nameNext, content.length());
        for (KubeConfig kubeConfig : kubeConfigs) {
            if (kubeConfig.getName().equals(namePrev)) {
                kubeConfig.setName(nameNext);
                kubeConfig.setContent(content);
                break;
            }
        }
        ServiceManager.getService(IResModel.class).fireCluster();
    }

    public void deleteByName(String name) {
        System.out.println("delete " + name);
        for (int i = 0; i < kubeConfigs.size(); i++) {
            KubeConfig kubeConfig = kubeConfigs.get(i);
            if (kubeConfig.getName().equals(name)) {
                kubeConfigs.remove(kubeConfig);
                break;
            }
        }
        ServiceManager.getService(IResModel.class).fireCluster();
    }

}
