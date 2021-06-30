package com.zx5435.idea.kubernetes.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KubeConfig {

    private String name;
    private String content;
    private String context;
    private String namespace;

    public static KubeConfig of(String name, String content) {
        KubeConfig ret = new KubeConfig();
        ret.setName(name);
        ret.setContent(content);
        return ret;
    }

}
