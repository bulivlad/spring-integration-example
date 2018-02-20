package ro.bulimac.vlad.springintegrationexample.channel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

/**
 * @author vladclaudiubulimac on 20/02/2018.
 */

@Configuration("emailOutboundChannelConfig")
public class EmailOutboundChannel {

    @Bean
    public MessageChannel emailOutboundChannel(){
        return new DirectChannel();
    }

}
