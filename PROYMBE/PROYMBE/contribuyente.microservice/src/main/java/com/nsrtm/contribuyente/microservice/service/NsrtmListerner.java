package com.nsrtm.contribuyente.microservice.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NsrtmListerner {

    private static final Logger logger = LogManager.getLogger(NsrtmListerner.class);

    @KafkaListener(topics ="nsrtm-topic", groupId ="nsrtmGroup")
    public void listen(String message) {
        logger.info("Mensaje recibido "+message);
    }

}
