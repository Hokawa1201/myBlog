package com.example.backend.services;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entities.Article;
import com.example.backend.entities.Follow;
import com.example.backend.entities.User;
import com.example.backend.mappers.ArticleMapper;
import com.example.backend.mappers.FollowMapper;
import com.example.backend.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FollowMapper followMapper;

    public List<JSONObject> getRecommendUser()
    {
        return userMapper.getMostFellowUsers(10);
    }

    public List<User> getFollowedUsersByUserId(Integer UserId){
        return followMapper.getFollowedUsersByUserId(UserId);
    }

    public List<User> getFansUsersByUserId(Integer UserId){
        return followMapper.getFansUsersByUserId(UserId);
    }

    public void updateUserInformation(User user) {
        userMapper.updateById(user);
    }

    public User getUserById(Integer user_id){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",user_id);
        return userMapper.selectOne(queryWrapper);
    }

    public void followUser(Integer userId, Integer followedUserId) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("follow_id",userId);
        queryWrapper.eq("followed_id",followedUserId);
        if(followMapper.exists(queryWrapper)){
            followMapper.delete(queryWrapper);
        }
        else {
            Follow follow = new Follow(userId,followedUserId);
            followMapper.insert(follow);
        }
    }

    public Boolean isFollowed(Integer userId, Integer followedUserId) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("follow_id",userId);
        queryWrapper.eq("followed_id",followedUserId);
        return followMapper.exists(queryWrapper);
    }
}

