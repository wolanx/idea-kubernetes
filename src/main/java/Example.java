import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.Config;

import java.io.IOException;

public class Example {

    // -Djdk.tls.client.protocols=TLSv1.2
    public static void main(String[] args) throws IOException, ApiException {
        String fPath = ClassLoader.getSystemClassLoader().getResource("kubeconfig").getFile();
        System.out.println("fPath = " + fPath);

        ApiClient client = Config.fromConfig(fPath);
        Configuration.setDefaultApiClient(client);

        CoreV1Api api = new CoreV1Api();
        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        for (V1Pod item : list.getItems()) {
            System.out.println(item.getMetadata().getName());
        }
    }

}