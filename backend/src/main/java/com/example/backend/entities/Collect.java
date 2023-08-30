package com.example.backend.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName(value = "collects")
public class Collect {
    @TableId
    private Integer id;
    private Integer articleId;
    private Integer userId;

    public Collect(Integer userId,Integer articleId){
        this.id = 0;
        this.articleId = articleId;
        this.userId = userId;
    }
}
