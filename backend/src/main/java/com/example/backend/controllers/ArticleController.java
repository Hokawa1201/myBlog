package com.example.backend.controllers;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.entities.Article;
import com.example.backend.entities.Comment;
import com.example.backend.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("getRecommendArticle")
    public List<Article> getRecommendArticles() {
        return articleService.getRecommendArticles();
    }

    @PostMapping("getArticlesByUserId")
    public List<Article> getArticlesByUserId(@RequestBody JSONObject body) {
        Integer userId = body.getInteger("userId");
        List<Article> result = articleService.getArticlesByUserId(userId);
        return result;
    }

    @PostMapping("getCollectArticlesByUserId")
    public List<Article> getCollectArticlesByUserId(@RequestBody JSONObject body) {
        return articleService.getCollectArticlesByUserId(body.getInteger("userId"));
    }

    @PostMapping("getLikeArticlesByUserId")
    public List<Article> getLikeArticlesByUserId(@RequestBody JSONObject body) {
        return articleService.getLikeArticlesByUserId(body.getInteger("userId"));
    }

    @PostMapping("getArticleById")
    public Article getArticleById(@RequestBody JSONObject body){
        Integer articleId = body.getInteger("articleId");
        return articleService.getArticleById(articleId);
    }

    @PostMapping("likeArticle")
    public void likeArticle(@RequestBody JSONObject body){
        Integer userId = body.getInteger("userId");
        Integer articleId = body.getInteger("articleId");
        articleService.LikeArticle(userId,articleId);
    }

    @PostMapping("collectArticle")
    public void collectArticle(@RequestBody JSONObject body){
        Integer userId = body.getInteger("userId");
        Integer articleId = body.getInteger("articleId");
        articleService.collectArticle(userId,articleId);
    }

    @PostMapping("isArticleLiked")
    public Boolean isArticleLiked(@RequestBody JSONObject body){
        Integer userId = body.getInteger("userId");
        Integer articleId = body.getInteger("articleId");
        return articleService.isArticleLiked(userId,articleId);
    }

    @PostMapping("isArticleCollected")
    public Boolean isArticleCollected(@RequestBody JSONObject body){
        Integer userId = body.getInteger("userId");
        Integer articleId = body.getInteger("articleId");
        return articleService.isArticleCollected(userId,articleId);
    }

    @PostMapping("updateArticle")
    public void updateArticle(@RequestBody Article article){
        articleService.updateArticle(article);
    }

    @PostMapping("getCommentsByArticleId")
    public List<JSONObject> getCommentsByArticleId(@RequestBody JSONObject body){
        Integer articleId = body.getInteger("articleId");
        return articleService.getCommentsByArticleId(articleId);
    }

    @PostMapping("likeComment")
    public void likeComment(@RequestBody JSONObject body){
        Integer userId = body.getInteger("userId");
        Integer commentId = body.getInteger("commentId");
        articleService.LikeComment(userId,commentId);
    }

    @PostMapping("isCommentLiked")
    public Boolean isCommentLiked(@RequestBody JSONObject body){
        Integer userId = body.getInteger("userId");
        Integer commentId = body.getInteger("commentId");
        return articleService.isCommentLiked(userId,commentId);
    }

    @PostMapping("publishComment")
    public void publishComment(@RequestBody JSONObject body){
        Integer userId = body.getInteger("userId");
        Integer articleId = body.getInteger("articleId");
        String content = body.getString("commentContent");
        articleService.publishComment(userId,articleId,content);
    }
}
