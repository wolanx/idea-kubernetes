package com.zx5435.idea.kubernetes.service;

import com.zx5435.idea.kubernetes.node.ITreeNode;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import org.junit.Test;

import java.util.List;

public class KubeUtilTest {


    @Test
    public void getByKind() {
        System.out.println(123);
        DefaultKubernetesClient client = new DefaultKubernetesClient();
        List<ITreeNode> arr = KubeUtil.listNs(client);
        for (ITreeNode one : arr) {
            System.out.println(one.getLabel());
        }

        System.out.println(KubeUtil.getDeployment(client, "zx5435", "blog"));
        System.out.println(KubeUtil.getPod(client, "zx5435", "blog-54958ff6f6-9sxgz"));
    }

}
