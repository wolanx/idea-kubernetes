package com.zx5435.idea.kubernetes.service;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Z:\www\zx5435\idea-kubernetes\build\idea-sandbox\config\options
 */
@State(name = "K8showStorage", storages = {@Storage(value = "k8show.plugin.xml")})
@Slf4j
public class K8showStorage implements PersistentStateComponent<K8showStorage> {

    @Getter
    public String stateValue;

    @Getter
    public Map<String, String> kubeconfigs;

    @Nullable
    @Override
    public K8showStorage getState() {
        return this;
    }

    @Override
    public void initializeComponent() {
        if (kubeconfigs == null) {
            kubeconfigs = new HashMap<>();
        }
    }

    @Override
    public void loadState(@NotNull K8showStorage state) {
        XmlSerializerUtil.copyBean(state, this);
        log.warn("loadState");
    }

    public void createByNameAndContent(String name, String content) {
        kubeconfigs.put(name, content);
        System.out.println(name + content.length());
    }

    public void updateByNameAndContent(String name, String content) {
        System.out.println("update " + name + " " + content);
    }

    public void deleteByName(String name) {
        System.out.println("delete " + name);
    }

}
