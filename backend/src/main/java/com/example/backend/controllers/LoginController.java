package com.example.backend.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.entities.User;
import com.example.backend.services.ArticleService;
import com.example.backend.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("hello")
    public User hello() {
        User user = loginService.Login("hhh","hhh");
        return user;
    }

    @PostMapping("register")
    public Boolean register(@RequestBody JSONObject body) {
        try {
            if (loginService.IsUsernameRegistered(body.getString("username"))) {
                return false;
            } else {
                java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
                loginService.AddUser(
                    body.getString("username"),
                    encryptSHA256(body.getString("password")),
                    date,
                    body.getString("email"),
                    body.getString("nickname")
                );
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("login")
    public User login(@RequestBody JSONObject body) {
        try {
            User user = loginService.Login(
                    body.getString("username"),
                    encryptSHA256(body.getString("password")));
            return user;
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] byteData = md.digest(input.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : byteData) {
            String hex = String.format("%02x", b);
            hexString.append(hex);
        }

        return hexString.toString();
    }

}