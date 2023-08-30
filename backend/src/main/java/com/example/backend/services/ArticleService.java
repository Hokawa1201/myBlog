package com.example.backend.services;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entities.*;
import com.example.backend.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentLikeMapper commentLikeMapper;

    public Integer AddArticle(Integer userid,String title,String content){
        Article article = new Article();
        article.setArticleId(0);
        article.setUserId(userid);
        article.setTitle(title);
        article.setContent(content);
        java.sql.Timestamp date = new java.sql.Timestamp(System.currentTimeMillis());
        article.setPublishTime(date);
        articleMapper.insert(article);
        return article.getArticleId();
    }

    public List<Article> getRecommendArticles() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("like_count");
        Page<Article> page = new Page<>(1,10);
        IPage<Article> pageResult = articleMapper.selectPage(page, queryWrapper);
        return pageResult.getRecords();
    }

    public List<Article> getArticlesByUserId(Integer userId){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return articleMapper.selectList(queryWrapper);
    }

    public List<Article> getCollectArticlesByUserId(Integer userId){
        return articleMapper.getCollectArticlesByUserId(userId);
    }

    public List<Article> getLikeArticlesByUserId(Integer userId){
        return articleMapper.getLikeArticlesByUserId(userId);
    }

    public Article getArticleById(Integer articleId){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id",articleId);
        return articleMapper.selectOne(queryWrapper);
    }

    public void LikeArticle(Integer userId,Integer articleId){
        QueryWrapper<Like> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("article_id",articleId);
        if(likeMapper.exists(queryWrapper)){
            likeMapper.delete(queryWrapper);
            UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("article_id", articleId);
            updateWrapper.setSql("like_count = like_count - 1");
            articleMapper.update(null, updateWrapper);
        }
        else {
            likeMapper.insert(new Like(userId,articleId));
            UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("article_id", articleId);
            updateWrapper.setSql("like_count = like_count + 1");
            articleMapper.update(null, updateWrapper);
        }
    }

    public void collectArticle(Integer userId,Integer articleId){
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("article_id",articleId);
        if(collectMapper.exists(queryWrapper)){
            collectMapper.delete(queryWrapper);
            UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("article_id", articleId);
            updateWrapper.setSql("collect_count = collect_count - 1");
            articleMapper.update(null, updateWrapper);
        }
        else {
            collectMapper.insert(new Collect(userId,articleId));
            UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("article_id", articleId);
            updateWrapper.setSql("collect_count = collect_count + 1");
            articleMapper.update(null, updateWrapper);
        }
    }

    public Boolean isArticleLiked(Integer userId, Integer articleId) {
        QueryWrapper<Like> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("article_id",articleId);
        return likeMapper.exists(queryWrapper);
    }

    public Boolean isArticleCollected(Integer userId, Integer articleId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("article_id",articleId);
        return collectMapper.exists(queryWrapper);
    }

    public void updateArticle(Article article) {
        articleMapper.updateById(article);
    }

    public List<JSONObject> getCommentsByArticleId(Integer articleId) {
        return commentMapper.getCommentsByArticleId(articleId);
    }

    public void LikeComment(Integer userId,Integer commentId){
        QueryWrapper<CommentsLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("comment_id",commentId);
        if(commentLikeMapper.exists(queryWrapper)){
            commentLikeMapper.delete(queryWrapper);
            UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", commentId);
            updateWrapper.setSql("comment_like_count = comment_like_count - 1");
            commentMapper.update(null, updateWrapper);
        }
        else {
            commentLikeMapper.insert(new CommentsLike(userId,commentId));
            UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", commentId);
            updateWrapper.setSql("comment_like_count = comment_like_count + 1");
            commentMapper.update(null, updateWrapper);
        }
    }

    public Boolean isCommentLiked(Integer userId, Integer commentId) {
        QueryWrapper<CommentsLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("comment_id",commentId);
        return commentLikeMapper.exists(queryWrapper);
    }

    public void publishComment(Integer userId,Integer articleId,String content){
        commentMapper.insert(new Comment(userId,articleId,content));
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("article_id", articleId);
        updateWrapper.setSql("comment_count = comment_count + 1");
        articleMapper.update(null, updateWrapper);
    }
}
