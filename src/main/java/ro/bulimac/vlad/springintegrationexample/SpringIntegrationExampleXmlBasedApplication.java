package ro.bulimac.vlad.springintegrationexample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import ro.bulimac.vlad.springintegrationexample.message.Email;

/**
 * @author vladclaudiubulimac on 20/02/2018.
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableIntegration
@ImportResource("integration.xml")
public class SpringIntegrationExampleXmlBasedApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringIntegrationExampleXmlBasedApplication.class);

    private final MessageChannel emailInboundChannelXml;

    @Autowired
    public SpringIntegrationExampleXmlBasedApplication(MessageChannel emailInboundChannelXml) {
        this.emailInboundChannelXml = emailInboundChannelXml;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationExampleXmlBasedApplication.class, args);
    }

    @Bean
	public CommandLineRunner commandLineRunner(){
		return (strings) -> {
            LOGGER.info("The app started");
            sendMessage(1L);
            sendMessage(2L);
            sendMessage(3L);
            sendMessage(4L);
		};
	}

    public void sendMessage(Long id){
        Email email = Email.builder().id(id).from("vlad").to("vlad").subject("test subject").body("test body").build();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonified = objectMapper.writeValueAsString(email);
            Message<String> dummyMessage = new GenericMessage<>(jsonified);
            emailInboundChannelXml.send(dummyMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOGGER.error("ERROR! The message was not sent");
        }
    }

}
