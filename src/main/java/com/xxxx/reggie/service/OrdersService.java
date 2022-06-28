package com.xxxx.reggie.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.reggie.entity.Orders;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author lu
 * @since 2022-06-28
 */
public interface OrdersService extends IService<Orders> {
    public void submit(Orders orders);
}
