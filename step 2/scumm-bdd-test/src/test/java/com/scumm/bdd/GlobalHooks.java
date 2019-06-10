package com.scumm.bdd;

import cucumber.api.java.Before;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class GlobalHooks {
    private static boolean dunit = false;

    @Before
    public void beforeAll() {
        if(!dunit) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                BddMicroPublisher.getInstance().close();
            }));
            dunit = true;
            try {
                BddMicroPublisher.getInstance().connect();
                MongoConnection.getInstance();
                ScummApi.getInstance();
            } catch (NoSuchAlgorithmException | KeyManagementException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
