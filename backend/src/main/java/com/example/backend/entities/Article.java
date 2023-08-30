package com.example.backend.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@TableName(value = "articles")
public class Article {
    @TableId(type = IdType.AUTO)
    private Integer articleId;
    @TableField(value = "user_id")
    private Integer userId;
    @TableField(value = "title")
    private String title;
    private String content;
    private Timestamp publishTime;
    private Integer likeCount = 0;
    private Integer collectCount = 0;
    private Integer commentCount = 0;
}
