package com.yshanchui.filters;

import io.netty.util.internal.StringUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class AuthGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");

        if(!StringUtils.hasText(token)){
            ServerHttpResponse response = exchange.getResponse();
            System.out.println("没有登陆");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);

            String ret ="{\"code\":401,\"msg\":\"没有登陆\",\"data\":\"\"}";
            byte[] bytes = ret.getBytes(StandardCharsets.UTF_8);

            HttpHeaders headers = response.getHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);

            DataBuffer wrap = response.bufferFactory().wrap(bytes);
            return response.writeWith(Mono.just(wrap));
        }

        return chain.filter(exchange);
    }
}
