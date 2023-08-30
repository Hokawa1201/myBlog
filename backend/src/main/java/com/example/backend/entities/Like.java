package com.example.backend.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName(value = "likes")
public class Like {
    private Integer userId;
    private Integer articleId;

    public Like(Integer userId,Integer articleId){
        this.userId = userId;
        this.articleId = articleId;
    }
}