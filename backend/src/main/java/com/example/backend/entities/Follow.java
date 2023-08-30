package com.example.backend.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName(value = "follows")
public class Follow {
    @TableId
    private Integer id;
    private Integer followedId;
    private Integer followId;

    public Follow(Integer followId,Integer followedId){
        id = 0;
        this.followId = followId;
        this.followedId = followedId;
    }
}