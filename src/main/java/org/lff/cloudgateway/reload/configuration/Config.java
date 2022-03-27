package org.lff.cloudgateway.reload.configuration;

import io.netty.handler.logging.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.gateway.config.HttpClientCustomizer;
import org.springframework.cloud.gateway.config.HttpClientFactory;
import org.springframework.cloud.gateway.config.HttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@Component
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    HttpClientProperties properties;

    @Autowired
    ServerProperties serverProperties;


    @Bean
    HttpClientFactory httpClientFactory() {
        List<HttpClientCustomizer> list = new ArrayList<>();
        list.add(httpClient -> {
            logger.info("Applying Logging filter to {}", httpClient);
            return httpClient.wiretap("LoggingFilter", LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL);
        });
        HttpClientFactory factory = new HttpClientFactory(properties, serverProperties, list);
        return factory;
    }
}
