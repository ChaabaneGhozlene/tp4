package com.example.ChaabaneGhozlene_TP4Gateway_3idl2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            System.out.println("Incoming request: " + exchange.getRequest().getURI());
            return chain.filter(exchange)
                        .then(Mono.fromRunnable(() -> 
                            System.out.println("Outgoing response: " + exchange.getResponse().getStatusCode())
                        ));
        };
    }

    public static class Config {
        // configuration possible
    }
}