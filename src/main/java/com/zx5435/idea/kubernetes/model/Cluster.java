package com.zx5435.idea.kubernetes.model;

import io.fabric8.kubernetes.api.model.AuthInfo;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.internal.KubeConfigUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.File;

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

    @SneakyThrows
    public DefaultKubernetesClient getClient() {
        if (name.equals("default")) {
            return new DefaultKubernetesClient();
        } else {
            io.fabric8.kubernetes.api.model.Config file = KubeConfigUtils.parseConfig(new File("C:\\Users\\admin\\Desktop\\kubeconfig.pref"));
            io.fabric8.kubernetes.api.model.Cluster cluster = file.getClusters().get(0).getCluster();
            AuthInfo user = file.getUsers().get(0).getUser();
            Config config = Config.empty();
            config.setMasterUrl(cluster.getServer());
            config.setCaCertData(cluster.getCertificateAuthorityData());
            config.setClientCertData(user.getClientCertificateData());
            config.setClientKeyData(user.getClientKeyData());
            return new DefaultKubernetesClient(config);
        }
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "name='" + name + '\'' +
                ", id='" + Integer.toHexString(hashCode()) + '\'' +
                '}';
    }

}
