package com.xxxx.reggie.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}
