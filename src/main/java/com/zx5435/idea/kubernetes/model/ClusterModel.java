package com.zx5435.idea.kubernetes.model;

import com.intellij.openapi.components.ServiceManager;
import io.fabric8.kubernetes.api.model.AuthInfo;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.internal.KubeConfigUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author zx5435
 */
@Slf4j
public class ClusterModel {

    @Getter
    private final String name;

    private final KubeConfig kubeConfig;

    @Getter
    private DefaultKubernetesClient client;

    public ClusterModel(KubeConfig kubeConfig) {
        this.name = kubeConfig.getName();
        this.kubeConfig = kubeConfig;

        try {
            initClient();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public String getNs() {
        return ServiceManager.getService(IResModel.class).getNsByClusterName(name);
    }

    public String getYaml() {
        return kubeConfig.getContent();
    }

    public void initClient() throws IOException {
        if ("default".equals(name)) {
            client = new DefaultKubernetesClient();
        } else {
            io.fabric8.kubernetes.api.model.Config file = KubeConfigUtils.parseConfigFromString(kubeConfig.getContent());
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
