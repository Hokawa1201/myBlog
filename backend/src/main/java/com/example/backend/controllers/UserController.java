package com.example.backend.controllers;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.entities.Article;
import com.example.backend.entities.User;
import com.example.backend.services.ArticleService;
import com.example.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("getRecommendUsers")
    public List<JSONObject> getRecommendUsers() {
        return userService.getRecommendUser();
    }

    @PostMapping("getFollowedUsersByUserId")
    public List<User> getFollowedUsersByUserId(@RequestBody JSONObject body){
        Integer userId = body.getInteger("userId");
        return userService.getFollowedUsersByUserId(userId);
    }

    @PostMapping("getFansUsersByUserId")
    public List<User> getFansUsersByUserId(@RequestBody JSONObject body){
        Integer userId = body.getInteger("userId");
        return userService.getFansUsersByUserId(userId);
    }

    @PostMapping("updateUserInformation")
    public void updateUserInformation(@RequestBody User user){
        userService.updateUserInformation(user);
    }

    @PostMapping("getUserById")
    public User getUserById(@RequestBody JSONObject body){
        return userService.getUserById(body.getInteger("userId"));
    }

    @PostMapping("followUser")
    public void followUser(@RequestBody JSONObject body){
        Integer userId = body.getInteger("userId");
        Integer followedUserId = body.getInteger("followedUserId");
        userService.followUser(userId,followedUserId);
    }

    @PostMapping("isFollowed")
    public Boolean isFollowed(@RequestBody JSONObject body){
        Integer userId = body.getInteger("userId");
        Integer followedUserId = body.getInteger("followedUserId");
        return userService.isFollowed(userId,followedUserId);
    }
}
