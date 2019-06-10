package com.scumm.bdd;

import com.clitellum.channels.configuration.ChannelConfiguration;
import com.clitellum.services.Identification;
import com.clitellum.services.Publisher;
import com.clitellum.services.PublisherBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class BddMicroPublisher {

    private static final Logger logger = LoggerFactory.getLogger(BddMicroPublisher.class);
    private static BddMicroPublisher instance;
    private Publisher publisher;

    public static synchronized BddMicroPublisher getInstance(){
        if (instance == null){
            instance = new BddMicroPublisher();
        }
        return instance;
    }

    private BddMicroPublisher() {
        ChannelConfiguration configuration = new ChannelConfiguration();

        configuration.getExchange().setName("mrnoow");
        String uri = System.getenv("MRNOOW_AMQP_SERVER")!=null? System.getenv("MRNOOW_AMQP_SERVER") : "amqp://micros_user:oJ3847PGtw3DCvbA1eeR2@localhost:5672";

        configuration.setUri(uri);

        publisher = PublisherBuilder.create()
                .setChannelSender(configuration)
                .setIdentification(Identification.create("BddPublisher", "BddPublisher"))
                .build();
    }

    public void connect() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        publisher.connect();
    }

    public <TMessage>  void send(String key, TMessage message)
    {
        try {
            publisher.send(key, message);
        } catch (NoSuchAlgorithmException | KeyManagementException | URISyntaxException e) {
            logger.error("Error al enviar", e);
        }
    }

    public void close(){
        try {
            publisher.close();
        } catch (IOException | TimeoutException e) {
            logger.error("Error al cerrar", e);
        }
    }
}
