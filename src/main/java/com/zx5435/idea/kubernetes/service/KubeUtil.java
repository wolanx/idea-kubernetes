package com.zx5435.idea.kubernetes.service;

import com.zx5435.idea.kubernetes.model.ClusterModel;
import com.zx5435.idea.kubernetes.node.*;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.fabric8.kubernetes.api.model.batch.v1.Job;
import io.fabric8.kubernetes.api.model.batch.v1beta1.CronJob;
import io.fabric8.kubernetes.api.model.networking.v1beta1.Ingress;
import io.fabric8.kubernetes.api.model.storage.StorageClass;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import io.fabric8.kubernetes.client.internal.SerializationUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 913332
 */
@Slf4j
public class KubeUtil {

    public static List<ITreeNode> getByKind(ClusterModel ctx, Class<?> kind) {
        DefaultKubernetesClient client = ctx.getClient();
        String ns = ctx.getNs();
        System.out.printf("Kube ctx=%s, ns=%s, kind=%s\n", ctx.getName(), ns, kind.getSimpleName());

        switch (kind.getSimpleName()) {
            case "Namespace":
                return listNs(client);
            // workload
            case "Deployment":
                return listDp(client, ns);
            case "StatefulSet":
                return listStatefulSet(client, ns);
            case "DaemonSet":
                return listDaemonSet(client, ns);
            case "Job":
                return listJob(client, ns);
            case "CronJob":
                return listCronJob(client, ns);
            case "Pod":
                return listPod(client, ns);
            case "CustomResourceDefinition":
                return listCustomResourceDefinition(client);
            // config
            case "ConfigMap":
                return listConfigMap(client, ns);
            case "Secret":
                return listSecrets(client, ns);
            // network
            case "Service":
                return listService(client, ns);
            case "Ingress":
                return listIngress(client, ns);
            // volume
            case "PersistentVolumeClaim":
                return listPersistentVolumeClaim(client, ns);
            case "PersistentVolume":
                return listPersistentVolume(client);
            case "StorageClass":
                return listStorageClass(client);
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

    // workload

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

    private static List<ITreeNode> listStatefulSet(DefaultKubernetesClient client, String ns) {
        List<StatefulSet> res = ns == null
                ? client.apps().statefulSets().inAnyNamespace().list().getItems()
                : client.apps().statefulSets().inNamespace(ns).list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (StatefulSet one : res) {
            ret.add(new StatefulSetNode(one));
        }
        return ret;
    }

    private static List<ITreeNode> listDaemonSet(DefaultKubernetesClient client, String ns) {
        List<DaemonSet> res = ns == null
                ? client.apps().daemonSets().inAnyNamespace().list().getItems()
                : client.apps().daemonSets().inNamespace(ns).list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (DaemonSet one : res) {
            ret.add(new DaemonSetNode(one));
        }
        return ret;
    }

    private static List<ITreeNode> listJob(DefaultKubernetesClient client, String ns) {
        List<Job> res = ns == null
                ? client.batch().v1().jobs().inAnyNamespace().list().getItems()
                : client.batch().v1().jobs().inNamespace(ns).list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (Job one : res) {
            ret.add(new JobNode(one));
        }
        return ret;
    }

    public static List<ITreeNode> listCronJob(DefaultKubernetesClient client, String ns) {
        List<CronJob> res = ns == null
                ? client.batch().v1beta1().cronjobs().inAnyNamespace().list().getItems()
                : client.batch().v1beta1().cronjobs().inNamespace(ns).list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (CronJob one : res) {
            ret.add(new CronJobNode(one));
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

    private static List<ITreeNode> listCustomResourceDefinition(DefaultKubernetesClient client) {
        List<CustomResourceDefinition> res = client.apiextensions().v1beta1().customResourceDefinitions().list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (CustomResourceDefinition one : res) {
            ret.add(new CustomResDefineNode(one));
        }
        return ret;
    }

    // network

    private static List<ITreeNode> listService(DefaultKubernetesClient client, String ns) {
        List<Service> res = ns == null
                ? client.services().inAnyNamespace().list().getItems()
                : client.services().inNamespace(ns).list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (Service one : res) {
            ret.add(new ServiceNode(one));
        }
        return ret;
    }

    private static List<ITreeNode> listIngress(DefaultKubernetesClient client, String ns) {
        List<Ingress> res = ns == null
                ? client.network().ingress().inAnyNamespace().list().getItems()
                : client.network().ingress().inNamespace(ns).list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (Ingress one : res) {
            ret.add(new IngressNode(one));
        }
        return ret;
    }

    // config

    public static List<ITreeNode> listConfigMap(DefaultKubernetesClient client, String ns) {
        List<ConfigMap> res = ns == null
                ? client.configMaps().inAnyNamespace().list().getItems()
                : client.configMaps().inNamespace(ns).list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (ConfigMap one : res) {
            ret.add(new ConfigMapNode(one));
        }
        return ret;
    }

    private static List<ITreeNode> listSecrets(DefaultKubernetesClient client, String ns) {
        List<Secret> res = ns == null
                ? client.secrets().inAnyNamespace().list().getItems()
                : client.secrets().inNamespace(ns).list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (Secret one : res) {
            ret.add(new SecretNode(one));
        }
        return ret;
    }

    // volume

    private static List<ITreeNode> listPersistentVolumeClaim(DefaultKubernetesClient client, String ns) {
        List<PersistentVolumeClaim> res = ns == null
                ? client.persistentVolumeClaims().inAnyNamespace().list().getItems()
                : client.persistentVolumeClaims().inNamespace(ns).list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (PersistentVolumeClaim one : res) {
            ret.add(new PvcNode(one));
        }
        return ret;
    }

    private static List<ITreeNode> listPersistentVolume(DefaultKubernetesClient client) {
        List<PersistentVolume> res = client.persistentVolumes().list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (PersistentVolume one : res) {
            ret.add(new PvNode(one));
        }
        return ret;
    }

    private static List<ITreeNode> listStorageClass(DefaultKubernetesClient client) {
        List<StorageClass> res = client.storage().storageClasses().list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (StorageClass one : res) {
            ret.add(new StorageClassNode(one));
        }
        return ret;
    }

    // yaml

    @SneakyThrows
    public static String getDeployment(DefaultKubernetesClient client, String ns, String name){
        Deployment myPod = client.apps().deployments().inNamespace(ns).withName(name).get();

        return SerializationUtils.dumpWithoutRuntimeStateAsYaml(myPod);
    }

    @SneakyThrows
    public static String getPod(DefaultKubernetesClient client, String ns, String name){
        Pod myPod = client.pods().inNamespace(ns).withName(name).get();

        return SerializationUtils.dumpWithoutRuntimeStateAsYaml(myPod);
    }

}
