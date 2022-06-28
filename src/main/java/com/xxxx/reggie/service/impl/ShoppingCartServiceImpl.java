package com.xxxx.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.reggie.entity.ShoppingCart;
import com.xxxx.reggie.mapper.ShoppingCartMapper;
import com.xxxx.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 *购物车
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
