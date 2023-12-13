package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return (RouteLocator) builder.routes()
				.route("CUSTOMER-SERVICE",
						r -> r.path("/customer/**").filters(q -> q.rewritePath("/customer/(?<segment>.*)", "/${segment}"))
								.uri("lb://CUSTOMER-SERVICE"))
				.route("CLIENT-SERVICE",
						r -> r.path("/client/**").filters(q -> q.rewritePath("/client/(?<segment>.*)", "/${segment}"))
								.uri("lb://CLIENT-SERVICE"))
				.build();

	}

}
