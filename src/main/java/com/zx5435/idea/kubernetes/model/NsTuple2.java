package com.zx5435.idea.kubernetes.model;

import lombok.Getter;
import lombok.Setter;

public class NsTuple2 {

    @Getter
    @Setter
    private String ctx;

    @Getter
    @Setter
    private String namespace;

    public static NsTuple2 of(String ctx, String namespace) {
        NsTuple2 ret = new NsTuple2();
        ret.setCtx(ctx);
        ret.setNamespace(namespace);
        return ret;
    }

    @Override
    public String toString() {
        return "NsTuple2{" +
                "ctx='" + ctx + '\'' +
                ", namespace='" + namespace + '\'' +
                ", id='" + Integer.toHexString(hashCode()) + '\'' +
                '}';
    }

}
