package com.zx5435.idea.kubernetes.service;

import com.intellij.openapi.util.io.FileUtil;
import com.zx5435.idea.kubernetes.node.ITreeNode;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Test
    public void cFile() throws IOException {
        Path tempDir = Paths.get(FileUtil.getTempDirectory(), "idea-kubernetes", "a.txt");

        FileUtils.write(tempDir.toFile(), "hehe", StandardCharsets.UTF_8, false);

    }

}
