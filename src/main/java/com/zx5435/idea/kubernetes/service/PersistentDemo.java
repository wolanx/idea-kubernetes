package com.zx5435.idea.kubernetes.service;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Z:\www\zx5435\idea-kubernetes\build\idea-sandbox\config\options
 */
@State(name = "PersistentDemo", storages = {@Storage(value = "PluginDemo.xml")})
@Slf4j
public class PersistentDemo implements PersistentStateComponent<PersistentDemo> {

    public String stateValue;

    @Nullable
    @Override
    public PersistentDemo getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull PersistentDemo state) {
        XmlSerializerUtil.copyBean(state, this);
        log.warn("loadState");
    }

    public void saveAny() {
        this.stateValue = "asnfdjkasflkjsaf";
    }

}
