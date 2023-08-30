package com.example.backend.controllers;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.services.ArticleService;
import com.example.backend.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class PublishController {
    @Autowired
    ArticleService articleService;

    @PostMapping("publish")
    public Integer PublishArticle(@RequestBody JSONObject body) {
        return articleService.AddArticle(
                body.getInteger("user_id"),
                body.getString("title"),
                body.getString("content")
        );
    }
}
