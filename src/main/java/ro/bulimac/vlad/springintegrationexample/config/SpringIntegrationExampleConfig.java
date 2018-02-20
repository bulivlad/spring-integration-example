package ro.bulimac.vlad.springintegrationexample.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author vladclaudiubulimac on 20/02/2018.
 */

@Configuration
@ConfigurationProperties(prefix = "app")
public class SpringIntegrationExampleConfig {

    @Getter
    @Setter
    private String queueName;

}
