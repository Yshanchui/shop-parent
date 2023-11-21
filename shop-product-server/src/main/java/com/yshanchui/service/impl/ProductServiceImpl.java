package com.yshanchui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yshanchui.domain.Product;
import com.yshanchui.mapper.ProductMapper;
import com.yshanchui.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl  extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
