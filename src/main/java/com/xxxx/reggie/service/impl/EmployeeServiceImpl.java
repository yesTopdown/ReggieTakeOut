package com.xxxx.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.reggie.entity.Employee;
import com.xxxx.reggie.mapper.EmployeeMapper;
import com.xxxx.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
