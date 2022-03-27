package com.faza.example.springcloudgatewayroutesfromdatabase.configuration;

import com.faza.example.springcloudgatewayroutesfromdatabase.service.ApiPathRouteLocatorImpl;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
  
  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
    return new ApiPathRouteLocatorImpl( routeLocatorBuilder);
  }
}
