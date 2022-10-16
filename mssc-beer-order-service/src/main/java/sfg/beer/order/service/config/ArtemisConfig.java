package sfg.beer.order.service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisConfigurationCustomizer;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ArtemisConfig implements ArtemisConfigurationCustomizer {

    @Value("${spring.artemis.host:localhost}")
    private String host;

    @Value("${spring.artemis.port:61616}")
    private int port;

    @Override
    public void customize(org.apache.activemq.artemis.core.config.Configuration configuration) {
        // Allow Artemis to accept tcp connections (Default port localhost:61616)
        final String uri = "tcp://" + host + ":" + port;
        try {
            configuration.addAcceptorConfiguration("netty", uri);
        } catch (Exception e) {
            log.error("Cannot set host/port for Artemis Server", e);
        }
    }
}
