package me.joseph.web.config.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class CustomizationConfig implements EmbeddedServletContainerCustomizer {
    @Value("${ing3.server.port}")
    private int port;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(port);
    }
}
