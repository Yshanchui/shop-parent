package com.yshanchui.fegin;

import com.yshanchui.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductFeignApi {

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id);
}
