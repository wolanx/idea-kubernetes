package com.zx5435.idea.kubernetes.service;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 913332
 */
public class MyApplicationServiceImpl implements MyApplicationService {

    @Override
    public List<String> getNamespace() {
        ArrayList<String> ret = new ArrayList<String>();

        ApiClient client;
        try {
            client = Config.fromConfig("C:\\Users\\admin\\.kube\\config");
            Configuration.setDefaultApiClient(client);

            CoreV1Api api = new CoreV1Api();
            V1NamespaceList list = api.listNamespace(null, null, null, null, null, null, null, null, null);
            for (V1Namespace item : list.getItems()) {
                String depName = item.getMetadata().getName();
                ret.add(depName);
            }
        } catch (IOException | ApiException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public List<String> getDeploy() {
        ArrayList<String> ret = new ArrayList<String>();

        ApiClient client;
        try {
            client = Config.fromConfig("C:\\Users\\admin\\.kube\\config");
            Configuration.setDefaultApiClient(client);

            AppsV1Api api = new AppsV1Api();
            V1DeploymentList list = api.listNamespacedDeployment("zx5435", null, null, null, null, null, null, null, null, null);
            for (V1Deployment item : list.getItems()) {
                String depName = item.getMetadata().getName();
                ret.add(depName);
            }
        } catch (IOException | ApiException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public List<String> getPod() {
        ArrayList<String> ret = new ArrayList<String>();

        ApiClient client;
        try {
            client = Config.fromConfig("C:\\Users\\admin\\.kube\\config");
            Configuration.setDefaultApiClient(client);

            CoreV1Api api = new CoreV1Api();
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

    public static void deletePod(String pName) {
        ApiClient client;
        try {
            client = Config.fromConfig("C:\\Users\\admin\\.kube\\config");
            Configuration.setDefaultApiClient(client);

            CoreV1Api api = new CoreV1Api();
            api.deleteNamespacedPod(pName, "zx5435", null, null, null, null, null, null);
        } catch (IOException | ApiException e) {
            e.printStackTrace();
        }
    }

}