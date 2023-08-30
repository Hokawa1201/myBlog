package com.example.backend.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "comments_likes")
public class CommentsLike {
    private Integer userId;
    private Integer commentId;

    public CommentsLike(Integer userId,Integer commentId){
        this.userId = userId;
        this.commentId = commentId;
    }
}