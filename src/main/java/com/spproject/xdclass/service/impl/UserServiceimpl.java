package com.spproject.xdclass.service.impl;

import com.spproject.xdclass.model.entity.User;
import com.spproject.xdclass.mapper.UserMapper;
import com.spproject.xdclass.service.UserService;
import com.spproject.xdclass.utils.CommonUtils;
import com.spproject.xdclass.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(Map<String, String> userInfo) {

        User user = parseToUser(userInfo);
        if (user != null){
           return userMapper.save(user);
        }
        else {return -1;}

    }

    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {

        User user = userMapper.findByPhoneAndPwd(phone, CommonUtils.MD5(pwd));
        if ( user == null){
            return null;
        }else {
            String token = JWTUtils.geneJsonWebToken(user);
            return token;
        }

    }

    private User parseToUser(Map<String, String> userInfo){
        if (userInfo.containsKey("phone") && userInfo.containsKey("pwd") && userInfo.containsKey("name")){
            User user = new User();
            user.setName(userInfo.get("name"));
            user.setHeadImg("");
            user.setCreateTime(new Date());
            user.setPhone(userInfo.get("phone"));
            String pwd = userInfo.get("pwd");

            user.setPwd(CommonUtils.MD5(pwd));

            return user;
        }
        else {return null;}
    }

    @Override
    public User findByUserId(Integer userId) {

        User user = userMapper.findByUserId(userId);
        return user;
    }
}
