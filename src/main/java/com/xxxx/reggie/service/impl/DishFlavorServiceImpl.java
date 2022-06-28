package com.xxxx.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.reggie.entity.DishFlavor;
import com.xxxx.reggie.mapper.DishFlavorMapper;
import com.xxxx.reggie.mapper.DishMapper;
import com.xxxx.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
