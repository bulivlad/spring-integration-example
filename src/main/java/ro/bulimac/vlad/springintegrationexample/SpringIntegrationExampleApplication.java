package ro.bulimac.vlad.springintegrationexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableAutoConfiguration
@EnableIntegration
public class SpringIntegrationExampleApplication {

	private final JmsTemplate jmsTemplate;

	@Autowired
	public SpringIntegrationExampleApplication(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationExampleApplication.class, args);
	}

	@Bean
	public IntegrationFlow integrationFlows(){
		return IntegrationFlows.from(s -> s.jms(jmsTemplate), c -> c.poller(Pollers.fixedRate(100)))
				.channel("emailInboundChannel")
				.channel("emailOutboundChannel")
				.get();
	}

}
