package ro.bulimac.vlad.springintegrationexample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

/**
 * @author vladclaudiubulimac on 20/02/2018.
 */

@Component
public class EmailOutboundChannelService {

    Logger LOGGER = LoggerFactory.getLogger(EmailOutboundChannelService.class);

    @ServiceActivator(inputChannel = "emailOutboundChannelXml")
    public void xmlMessageLogging(String message) {
        LOGGER.info("We received a message on xml channel");
        LOGGER.info(message);
    }

    @ServiceActivator(inputChannel = "emailOutboundChannel")
    public void messageLogging(String message) {
        LOGGER.info("We received a message");
        LOGGER.info(message);
    }

}
