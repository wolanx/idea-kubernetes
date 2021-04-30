package com.zx5435.idea.kubernetes.service;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 913332
 */
public class MyApplicationServiceImpl implements MyApplicationService {

    @Override
    public List<String> getDp() {
        ArrayList<String> ret = new ArrayList<String>();

        ApiClient client;
        try {
            client = Config.fromConfig("C:\\Users\\913332\\kubeconfig");
            Configuration.setDefaultApiClient(client);

            CoreV1Api api = new CoreV1Api();
//            V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
            V1PodList list = api.listNamespacedPod("zx5435", null, null, null, null, null, null, null, null, null);
            for (V1Pod item : list.getItems()) {
                String depName = item.getMetadata().getName();
                ret.add(depName);
            }
        } catch (IOException | ApiException e) {
            e.printStackTrace();
        }

        return ret;
    }

}
