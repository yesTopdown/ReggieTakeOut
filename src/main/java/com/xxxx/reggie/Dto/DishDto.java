package com.xxxx.reggie.Dto;

import com.xxxx.reggie.entity.Dish;
import com.xxxx.reggie.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {
    //菜品的口味数据
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName; //dish表categoryId ->categoryName

    private Integer copies;
}
