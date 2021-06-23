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

    @Getter
    @Setter
    private DefaultKubernetesClient client;

    public Cluster(String name, IResModel model) {
        this.name = name;
        this.model = model;

        initClient();
    }

    public String getNs() {
        return getModel().getNsByCtx(this);
    }

    @SneakyThrows
    public void initClient() {
        if ("default".equals(name)) {
            client = new DefaultKubernetesClient();
        } else {
            io.fabric8.kubernetes.api.model.Config file = KubeConfigUtils.parseConfig(new File(System.getProperty("user.home") + "\\Desktop\\kubeconfig.perf"));
            io.fabric8.kubernetes.api.model.Cluster cluster = file.getClusters().get(0).getCluster();
            AuthInfo user = file.getUsers().get(0).getUser();
            Config config = Config.empty();
            config.setMasterUrl(cluster.getServer());
            config.setCaCertData(cluster.getCertificateAuthorityData());
            config.setClientCertData(user.getClientCertificateData());
            config.setClientKeyData(user.getClientKeyData());
            client = new DefaultKubernetesClient(config);
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
