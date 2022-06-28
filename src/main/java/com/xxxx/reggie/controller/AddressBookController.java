package com.xxxx.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xxxx.reggie.common.BaseContext;
import com.xxxx.reggie.common.R;
import com.xxxx.reggie.entity.AddressBook;
import com.xxxx.reggie.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *地址簿管理器
 */
@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;
    /**
     * 新增
     */
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook){
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook:{}",addressBook);
        addressBookService.save(addressBook);
        return R.success(addressBook);
    }
    /**
     * 默认地址
     */
    @PutMapping("default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook){
        log.info("addressBook:{}",addressBook);
        LambdaUpdateWrapper<AddressBook> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(AddressBook::getUserId,BaseContext.getCurrentId()).set(AddressBook::getIsDefault,0);
        //先把所有用户的默认地址改为0
        //update address_book set is_default=0 where user_id=?
        addressBookService.update(wrapper);
        //在单独修改默认地址
        addressBook.setIsDefault(1);
        //update address_book set is_default=1 where user_id=?
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }
    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public R<Object> get(@PathVariable Long id){
        AddressBook addressBook=addressBookService.getById(id);
        if(addressBook!=null){
            return R.success(addressBook);
        }else {
            return R.error("没有找到该对象");
        }
    }
    /**
     * 查询默认地址
     */
    @GetMapping("default")
    public R<AddressBook> getDefault(){
        LambdaQueryWrapper<AddressBook> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId,BaseContext.getCurrentId());
        queryWrapper.eq(AddressBook::getIsDefault,1);
        //select * from address_book where user_id=? and is_default=1
        AddressBook addressBook=addressBookService.getOne(queryWrapper);
        if(null==addressBook){
            return R.error("没有找到该对象");
        }else {
            return R.success(addressBook);
        }
    }
    /**
     * 查询指定用户的全部地址
     */
    @GetMapping("/list")
    public R<List<AddressBook>> list(AddressBook addressBook){
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook:{}",addressBook);
        //条件构造器
        LambdaQueryWrapper<AddressBook> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=addressBook.getUserId(),AddressBook::getUserId,addressBook.getUserId());
        queryWrapper.orderByDesc(AddressBook::getUpdateTime);
        //select* from address_book where user_id=? order by update_time desc
        return R.success(addressBookService.list(queryWrapper));
    }
}
