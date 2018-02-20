package ro.bulimac.vlad.springintegrationexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

/**
 * @author vladclaudiubulimac on 20/02/2018.
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableIntegration
@ImportResource("integration.xml")
public class SpringIntegrationExampleXmlBasedApplication {

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
			Message<String> dummyMessage = new GenericMessage<>("{\"message\":\"test message\"}");

			emailInboundChannelXml.send(dummyMessage);
		};
	}


}
