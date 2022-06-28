package com.xxxx.reggie.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.reggie.entity.OrderDetail;
import com.xxxx.reggie.mapper.OrderDetailMapper;
import com.xxxx.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author lu
 * @since 2022-06-28
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
