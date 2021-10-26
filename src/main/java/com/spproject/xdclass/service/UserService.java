package com.spproject.xdclass.service;

import com.spproject.xdclass.model.entity.User;

import java.util.Map;

public interface UserService {

    int save(Map<String, String> userInfo);

    String findByPhoneAndPwd(String phone, String pwd);

    User findByUserId(Integer userId);



}
