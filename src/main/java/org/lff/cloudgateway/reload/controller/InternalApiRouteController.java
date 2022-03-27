package org.lff.cloudgateway.reload.controller;

import org.lff.cloudgateway.reload.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class InternalApiRouteController {

  @Autowired
  private GatewayRouteService service;

  @GetMapping("/refresh")
  public Flux<String> route1() {
    service.refreshRoutes();
    return Flux.just("Refresh request sent.");
  }
}
