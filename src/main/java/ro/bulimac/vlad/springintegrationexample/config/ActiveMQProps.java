package ro.bulimac.vlad.springintegrationexample.config;

import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author vladclaudiubulimac on 20/02/2018.
 */

@Configuration
public class ActiveMQProps {

    @Bean
    public ActiveMQProperties activeMQProperties(){
        return new ActiveMQProperties();
    }

}
