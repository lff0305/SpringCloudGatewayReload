package com.faza.example.springcloudgatewayroutesfromdatabase.controller;

import com.faza.example.springcloudgatewayroutesfromdatabase.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class InternalApiRouteController {

  @Autowired
  private GatewayRouteService service;

  @GetMapping("/refresh")
  public Flux<String> route1() {
    service.refreshRoutes();
    return Flux.fromIterable(Arrays.asList("OK"));
  }
}
