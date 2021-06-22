package com.zx5435.idea.kubernetes.service;

import com.zx5435.idea.kubernetes.model.Cluster;
import com.zx5435.idea.kubernetes.node.*;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.batch.v1beta1.CronJob;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 913332
 */
@Slf4j
public class KubeUtil {

    public static List<ITreeNode> getByKind(Cluster ctx, Class<?> kind) {
        String ns = ctx.getNs();
        System.out.printf("Kube ctx=%s, ns=%s, kind=%s\n", ctx.getName(), ns, kind.getSimpleName());

        switch (kind.getSimpleName()) {
            case "Namespace":
                return listNs(ctx.getClient());
            case "Deployment":
                return listDp(ctx.getClient(), ns);
            case "Pod":
                return listPod(ctx.getClient(), ns);
            case "CronJob":
                return listCronJob();
            case "ConfigMap":
                return listConfigMap();
            default:
                return Collections.emptyList();
        }
    }


    public static List<ITreeNode> listNs(DefaultKubernetesClient client) {
        List<Namespace> res = client.namespaces().list().getItems();

        res.forEach(NamespaceNode::new);

        List<ITreeNode> ret = new ArrayList<>();
        Namespace ns = new Namespace();
        ns.setMetadata(new ObjectMeta());
        ret.add(new NamespaceNode(ns));
        for (Namespace one : res) {
            ret.add(new NamespaceNode(one));
        }
        return ret;
    }

    public static List<ITreeNode> listDp(DefaultKubernetesClient client, String ns) {
        List<Deployment> res = ns == null
                ? client.apps().deployments().inAnyNamespace().list().getItems()
                : client.apps().deployments().inNamespace(ns).list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (Deployment one : res) {
            ret.add(new DeploymentNode(one));
        }
        return ret;
    }

    public static List<ITreeNode> listPod(DefaultKubernetesClient client, String ns) {
        List<Pod> res = ns == null
                ? client.pods().inAnyNamespace().list().getItems()
                : client.pods().inNamespace(ns).list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (Pod one : res) {
            ret.add(new PodNode(one));
        }
        return ret;
    }

    public static void listPodWatch() {
        DefaultKubernetesClient client = new DefaultKubernetesClient();

        client.pods().inAnyNamespace().watch(new Watcher<Pod>() {
            @Override
            public void eventReceived(Action action, Pod resource) {
                System.out.println("resource = " + resource.getMetadata().getName());
            }

            @Override
            public void onClose(WatcherException cause) {
                System.out.println("cause = " + cause);
            }
        });
        System.out.println();
    }

    public static List<ITreeNode> listCronJob() {
        DefaultKubernetesClient client = new DefaultKubernetesClient();
        List<CronJob> res = client.batch().v1beta1().cronjobs().inAnyNamespace().list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (CronJob one : res) {
            ret.add(new CronJobNode(one));
        }
        return ret;
    }

    public static List<ITreeNode> listConfigMap() {
        DefaultKubernetesClient client = new DefaultKubernetesClient();
        List<ConfigMap> res = client.configMaps().inAnyNamespace().list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (ConfigMap one : res) {
            ret.add(new ConfigMapNode(one));
        }
        return ret;
    }

}
