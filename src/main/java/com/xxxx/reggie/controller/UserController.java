package com.xxxx.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xxxx.reggie.Utils.ValidateCodeUtils;
import com.xxxx.reggie.common.R;
import com.xxxx.reggie.entity.User;
import com.xxxx.reggie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *发送手机短信
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        //获取手机号
        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)) {
            //生成随机4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            //调用短信服务API完成发送短信
            //穷买不起阿里云
            log.info("验证码code:{}",code);
            //需要将生成的验证码保存到session
            session.setAttribute(phone,code);
            return R.success("验证码发送成功");
        }
        return R.error("短信发送失败");
    }

    /**
     * 移动端
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
       log.info(map.toString());
        //获取手机号
        String phone = map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();
        //从Session中获取保存的验证码
        Object codeInSession = session.getAttribute(phone);
        //比对验证码
        if(codeInSession!=null&&codeInSession.equals(code)){
            //能够比对成功，说明登录成功
            LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if(user==null){
                //判断手机号用户对应是否为新用户，是新用户自动完成注册
                user=new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            return R.success(user);
        }
        return R.error("登录失败");
    }
}
