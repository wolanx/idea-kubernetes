package com.zx5435.idea.kubernetes.tree.model;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;

import java.util.List;

/**
 * @author 913332
 */
public class TestModel {

    public static void main(String[] args) {
        DefaultKubernetesClient client = new DefaultKubernetesClient();
        List<Namespace> res = client.namespaces().list().getItems();

        System.out.println();
    }

}
