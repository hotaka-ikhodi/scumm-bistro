package com.scumm.core.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public abstract class MicroRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MicroRunner.class);

    public abstract MicroBean getMicroBean();

    @Override
    public void run(String... args) throws Exception {
        logger.info("Application started to kill this application, press Ctrl + C.");
        logger.info("Iniciando servicio {}", this.getMicroBean().getName());
        this.getMicroBean().run();
    }
}
