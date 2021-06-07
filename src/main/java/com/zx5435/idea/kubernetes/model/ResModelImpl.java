package com.zx5435.idea.kubernetes.model;

import com.zx5435.idea.kubernetes.node.*;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.batch.v1beta1.CronJob;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 913332
 */
@Slf4j
public class ResModelImpl implements ResModel {

    @Override
    public List<ContextNode> getAllContexts() {
        List<ContextNode> objects = new ArrayList<>();
        objects.add(new ContextNode("aaaa"));
        objects.add(new ContextNode("bbbb"));
        return objects;
    }

    @Override
    public List<ITreeNode> getResByKind(Class<?> kind) {
        log.warn("getResByKind." + kind.getSimpleName());

        switch (kind.getSimpleName()) {
            case "Namespace":
                return listNs();
            case "Deployment":
                return listDp();
            case "Pod":
                return listPod();
            case "CronJob":
                return listCronJob();
            default:
                return Collections.emptyList();
        }
    }

    public List<ITreeNode> listNs() {
        DefaultKubernetesClient client = new DefaultKubernetesClient();
        List<Namespace> res = client.namespaces().list().getItems();

        res.forEach(NamespaceNode::new);

        List<ITreeNode> ret = new ArrayList<>();
        for (Namespace one : res) {
            ret.add(new NamespaceNode(one));
        }
        return ret;
    }

    public List<ITreeNode> listDp() {
        DefaultKubernetesClient client = new DefaultKubernetesClient();
        List<Deployment> res = client.apps().deployments().inAnyNamespace().list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (Deployment one : res) {
            ret.add(new DeploymentNode(one));
        }
        return ret;
    }

    public List<ITreeNode> listPod() {
        DefaultKubernetesClient client = new DefaultKubernetesClient();
        List<Pod> res = client.pods().inAnyNamespace().list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (Pod one : res) {
            ret.add(new PodNode(one));
        }
        return ret;
    }

    public List<ITreeNode> listCronJob() {
        DefaultKubernetesClient client = new DefaultKubernetesClient();
        List<CronJob> res = client.batch().v1beta1().cronjobs().inAnyNamespace().list().getItems();

        List<ITreeNode> ret = new ArrayList<>();
        for (CronJob one : res) {
            ret.add(new CronJobNode(one));
        }
        return ret;
    }

}
