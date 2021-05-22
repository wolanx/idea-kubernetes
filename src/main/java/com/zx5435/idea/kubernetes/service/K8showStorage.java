package com.zx5435.idea.kubernetes.service;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
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

    private String stateValue;

    private Map<String, String> kubeconfigMap;

//    @Override
//    public void initializeComponent() {
//        if (kubeconfigMap == null) {
//            kubeconfigMap = new HashMap<>();
//        }
//    }

    @Nullable
    @Override
    public K8showStorage getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull K8showStorage state) {
        XmlSerializerUtil.copyBean(state, this);
        log.warn("loadState");
    }

    public void saveAny(String name, String content) {
        this.stateValue = name;
        if (kubeconfigMap == null) {
            kubeconfigMap = new HashMap<>();
        }
        kubeconfigMap.put(name, content);
        System.out.println(name);
        System.out.println(content);
    }

}
