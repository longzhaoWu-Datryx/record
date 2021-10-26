package com.spproject.xdclass.mapper;

import com.spproject.xdclass.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int save(User user);

    User findByPhone(@Param("phone") String phone);

    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);

    User findByUserId(@Param("user_id") int userId);


}
