package com.example.backend.services;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entities.User;
import com.example.backend.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Wrapper;

@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    public void AddUser(String username, String password, Date date,String email,String nickname) {
        User user = new User();
        user.setId(0);
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setRegisterDate(date);
        user.setEmail(email);
        userMapper.insert(user);
    }

    public Boolean IsUsernameRegistered(String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return userMapper.exists(queryWrapper);
    }

    public User Login(String username,String password){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username)
                .eq("password",password);
        return userMapper.selectOne(queryWrapper);
    }



}
