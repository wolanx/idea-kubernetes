package com.zx5435.idea.kubernetes.service;

/**
 * @author 913332
 */
public class MyApplicationServiceImpl implements MyApplicationService {

    //    public static final String C_USERS_ADMIN_KUBE_CONFIG = "C:\\Users\\admin\\.kube\\config";
    public static final String C_USERS_ADMIN_KUBE_CONFIG = System.getProperty("user.home") + "\\.kube\\config";

}
