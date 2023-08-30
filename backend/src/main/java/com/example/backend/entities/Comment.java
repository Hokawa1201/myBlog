package com.example.backend.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@TableName(value = "comments")
public class Comment {
    @TableId
    private Integer id;
    private Integer articleId;
    private Integer userId;
    private Timestamp commentTime;
    private Integer commentLikeCount;
    private String commentContent;

    public Comment(Integer userId,Integer articleId,String content){
        this.id = 0;
        this.userId = userId;
        this.articleId = articleId;
        this.commentContent = content;
        this.commentTime = new Timestamp(System.currentTimeMillis());
        this.commentLikeCount = 0;
    }
}
