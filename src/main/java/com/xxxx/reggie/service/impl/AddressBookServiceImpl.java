package com.xxxx.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.reggie.entity.AddressBook;
import com.xxxx.reggie.mapper.AddressBookMapper;
import com.xxxx.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
