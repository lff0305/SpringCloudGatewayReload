package com.faza.example.springcloudgatewayroutesfromdatabase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.QueryRoutePredicateFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import reactor.core.publisher.Flux;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class ApiPathRouteLocatorImpl implements RouteLocator {

  private static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  private final RouteLocatorBuilder routeLocatorBuilder;

  private boolean first = true;

  public ApiPathRouteLocatorImpl(RouteLocatorBuilder routeLocatorBuilder) {
    this.routeLocatorBuilder = routeLocatorBuilder;
  }

  @Override
  public Flux<Route> getRoutes() {

    logger.info("getRoutes called {}", this.first);

    RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();

    if (first) {
      first = false;
      return Flux.fromArray(new Route[0]);
    }

    QueryRoutePredicateFactory.Config config = new QueryRoutePredicateFactory.Config().setParam("name").setRegexp("foo");
    List<String> paths = new ArrayList<>();
    paths.add("/**");
    PathRoutePredicateFactory.Config pathConfig = new PathRoutePredicateFactory.Config().setPatterns(paths);

    Flux<Route> result = routesBuilder.route(
            "integration-test", r -> r
                    .predicate(new QueryRoutePredicateFactory().apply(config)).and()
                    .predicate(new PathRoutePredicateFactory().apply(pathConfig))
                    .filters(f->f.rewritePath("/integration/(?<segment>.*)","/a-integration/${segment}"))
                    .uri("http://httpbin.org")
    ).build().getRoutes();
    return result;
  }
}
