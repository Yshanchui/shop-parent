package com.yshanchui.filters.factory;

import com.yshanchui.filters.TImeGatewayFilterParam;
import com.yshanchui.filters.TimeGatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Component
public class TimeGatewayFilterFactory extends AbstractGatewayFilterFactory<TImeGatewayFilterParam> {

    public TimeGatewayFilterFactory(){
        super(TImeGatewayFilterParam.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("value1","value2");
    }

    @Override
    public GatewayFilter apply(TImeGatewayFilterParam config) {
        System.out.println(config.getValue1());
        System.out.println(config.getValue2());
        return new TimeGatewayFilter();
    }
//    @Override
//    public GatewayFilter apply(Object config) {
//        return new TimeGatewayFilter();
//    }


}
