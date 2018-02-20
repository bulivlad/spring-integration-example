package ro.bulimac.vlad.springintegrationexample.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * @author vladclaudiubulimac on 20/02/2018.
 */

@Configuration
public class QueueConfig {

    private final ActiveMQProperties activeMQProperties;

    private final SpringIntegrationExampleConfig appConfig;

    @Autowired
    public QueueConfig(ActiveMQProperties activeMQProperties, SpringIntegrationExampleConfig appConfig) {
        this.activeMQProperties = activeMQProperties;
        this.appConfig = appConfig;
    }

    @Bean
    public ActiveMQQueue activeMqQueue(){
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(appConfig.getQueueName());
        return activeMQQueue;
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setUserName(activeMQProperties.getUser());
        activeMQConnectionFactory.setPassword(activeMQProperties.getPassword());
        activeMQConnectionFactory.setBrokerURL(activeMQProperties.getBrokerUrl());

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setSessionCacheSize(10);
        cachingConnectionFactory.setTargetConnectionFactory(activeMQConnectionFactory);
        return cachingConnectionFactory;
    }

}
