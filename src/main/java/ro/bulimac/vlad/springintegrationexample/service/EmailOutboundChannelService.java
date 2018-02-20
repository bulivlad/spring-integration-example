package ro.bulimac.vlad.springintegrationexample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import ro.bulimac.vlad.springintegrationexample.message.Email;

import java.io.IOException;

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
        convertAndPrintMessage(message);
    }

    private void convertAndPrintMessage(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Email emailMessage = mapper.readValue(message, Email.class);
            LOGGER.info("Formatted message");
            LOGGER.info(emailMessage.toString());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("ERROR! The message was not sent");
        }
    }

    @ServiceActivator(inputChannel = "emailOutboundChannel")
    public void messageLogging(String message) {
        LOGGER.info("We received a message");
        LOGGER.info(message);
        convertAndPrintMessage(message);
    }

}
