package com.zx5435.idea.kubernetes.model;

import lombok.Getter;
import lombok.Setter;

public class Cluster {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private IResModel model;

    public static Cluster of(String name, IResModel model) {
        Cluster ret = new Cluster();
        ret.setName(name);
        ret.setModel(model);
        return ret;
    }

    public String getNs() {
        return getModel().getNsByCtx(this);
    }


    @Override
    public String toString() {
        return "Cluster{" +
                "name='" + name + '\'' +
                ", id='" + Integer.toHexString(hashCode()) + '\'' +
                '}';
    }

}
