package com.scumm.core.microservices;

import com.clitellum.MicroServiceBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@PropertySource("classpath:application.properties")
public abstract class MicroBean implements Runnable {


    private static final Logger logger = LoggerFactory.getLogger(MicroBean.class);
    private boolean running = false;

    @Autowired
    private MicroSpringContainer container;

    @Value("${message.rabbitmq.server}")
    protected String rabbitServer;

    public abstract String getName();

    public abstract List<MicroServiceBase> getServices();

    public MicroBean() {
   }

    @PostConstruct
    public void init() {
        logger.info("init");
        running = false;
    }

    @Override
    public void run() {
        logger.info("Connexion a Rabbit: {}", this.rabbitServer);
        running = true;
        for (MicroServiceBase micro :
                this.getServices()) {
            logger.info("running service");
            micro.setContainer(container);
            micro.run();
        }
        while (running){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Error Sleep", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    @PreDestroy
    public void destroy() throws IOException, TimeoutException {
        if (! this.running)
            return;

        logger.info("wait to stop");
        running = false;
        this.stop();
        logger.info("destroy");
    }

    private void stop() throws IOException, TimeoutException {
        for (MicroServiceBase micro :
                this.getServices()) {
            logger.info("stopping");
            if (micro!= null)
                micro.stop();
            else
                logger.info("Micro null");
        }
    }
}
