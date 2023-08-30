package com.example.backend.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entities.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("SELECT articles.* FROM articles,collects\n" +
            "WHERE collects.user_id=#{userId} AND articles.article_id = collects.article_id")
    public List<Article> getCollectArticlesByUserId(@Param("userId") int userId);

    @Select("SELECT articles.* FROM articles,likes\n" +
            "WHERE likes.user_id=#{userId} AND articles.article_id = likes.article_id")
    public List<Article> getLikeArticlesByUserId(@Param("userId") int userId);
}
