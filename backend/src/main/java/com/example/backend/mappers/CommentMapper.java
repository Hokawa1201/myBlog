package com.example.backend.mappers;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entities.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("SELECT users.nickname AS nickname,comments.*\n" +
            "FROM users,comments\n" +
            "WHERE users.id = comments.user_id and article_id = #{article_id}")
    List<JSONObject> getCommentsByArticleId(@Param("article_id") Integer articleId);
}
