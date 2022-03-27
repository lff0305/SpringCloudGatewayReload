package org.lff.cloudgateway.reload.configuration;

import org.lff.cloudgateway.reload.service.ReloadRouteLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
  
  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
    return new ReloadRouteLocator( routeLocatorBuilder);
  }
}
