package com.xxxx.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.reggie.Dto.DishDto;
import com.xxxx.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish dish_flavor
    public void saveWithFlavor(DishDto dishDto);
    //根据id查询菜品对应的口味信息
    public DishDto getByIdWithFlavor(Long id);
    //修改口味
    void updateWithFlavor(DishDto dishDto);

}
