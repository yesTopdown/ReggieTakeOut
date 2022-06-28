package com.xxxx.reggie.controller;


import com.xxxx.reggie.common.R;
import com.xxxx.reggie.entity.Orders;
import com.xxxx.reggie.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author lu
 * @since 2022-06-28
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    /**
     * 用户下单
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("用户下单----");
        ordersService.submit(orders);
        return R.success("下单成功");
    }
}
