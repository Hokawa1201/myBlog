package com.example.backend.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName(value = "users")
public class User {
    private Integer id;
    @TableId
    private String username;
    private String password;
    private Date registerDate;
    private String email;
    private String nickname;
}
