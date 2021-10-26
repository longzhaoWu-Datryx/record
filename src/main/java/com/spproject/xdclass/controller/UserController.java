package com.spproject.xdclass.controller;

import com.spproject.xdclass.model.entity.User;
import com.spproject.xdclass.model.request.LoginRequest;
import com.spproject.xdclass.service.UserService;
import com.spproject.xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public JsonData  register(@RequestBody Map<String, String> userInfo){

        int rows = userService.save(userInfo);

        return rows == 1 ? JsonData.buildSuccess() : JsonData.buildError("注册失败，请重试");

    }

    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){

        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(), loginRequest.getPwd());

        return  token == null ? JsonData.buildError("登录失败，账号密码错误"):JsonData.buildSuccess(token);
    }


    @GetMapping("find_by_token")
    public JsonData findUserInfonByToken(HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");

        if (userId == null){
            return JsonData.buildError("查询失败");
        }

        User user = userService.findByUserId(userId);
        return JsonData.buildSuccess(user);
    }

}
