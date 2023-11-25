package com.yshanchui.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class TimeGatewayFilter implements GatewayFilter, Ordered {

    /**
     * 过滤逻辑
     * @param exchange 转换器--封装了来自请求中所有信息，比如：请求方法，请求参数，请求路径，请求头，cookie等
     * @param chain 过滤器链--使用责任链模式，决定当前过滤器是放行还是拒绝
     * @return Mono 响应式编程的返回值规范，一般后置拦截才会用
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long begin = System.currentTimeMillis();
        System.out.println("前置当前时间：" + begin);
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            long end = System.currentTimeMillis();
            System.out.println("后置当前时间：" + end);
            System.out.println("两者时间差:" + (end - begin));
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
