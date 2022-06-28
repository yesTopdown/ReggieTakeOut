package com.xxxx.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.reggie.common.CustomException;
import com.xxxx.reggie.entity.Category;
import com.xxxx.reggie.entity.Dish;
import com.xxxx.reggie.entity.Setmeal;
import com.xxxx.reggie.mapper.CategoryMapper;
import com.xxxx.reggie.service.CategoryService;
import com.xxxx.reggie.service.DishService;
import com.xxxx.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private DishService dishService;
    @Resource
    private SetmealService setmealService;


    /**
     * 根据id删除分类，删除之前需要进行判断
     * 菜品分类：id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件,根据分类id进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        //查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
        if (count1 > 0) {
            //已经关联，抛出一个业务异常
            throw new CustomException("当前分类关联了菜品，不能删除");
        }
        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if (count2 > 0) {
            //已经关联，抛出一个业务异常
            throw new CustomException("当前分类关联了套餐，不能删除");
        }
        //正常删除分类
        super.removeById(id);
    }
}
