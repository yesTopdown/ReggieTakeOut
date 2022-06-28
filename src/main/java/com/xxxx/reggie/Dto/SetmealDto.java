package com.xxxx.reggie.Dto;

import com.xxxx.reggie.entity.Setmeal;
import com.xxxx.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
