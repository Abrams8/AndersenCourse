package com.shop.gateway.authFilter;

import com.shop.gateway.entity.Users;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    public static class Config{
    }

    private final WebClient.Builder webClientBuilder;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                throw new RuntimeException("Missing auth info");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            String[] parts = authHeader.split(" ");

            if(parts.length !=2 || !"Bearer".equals(parts[0])){
                throw new RuntimeException("Incorrect auth structure");
            }

            return webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8081/api/v1/auth/validateToken?token=" + parts[1])
                    .retrieve().bodyToMono(Users.class)
                    .map(userDto -> {
                        exchange.getRequest()
                                .mutate()
                                .header("username", userDto.getUserName())
                                .header("role", userDto.getUserRole());
                        return exchange;
                    }).flatMap(chain::filter);

        });
    }
}

