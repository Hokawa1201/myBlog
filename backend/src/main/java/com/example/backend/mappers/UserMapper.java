package com.example.backend.mappers;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT ui.*,COALESCE(followers_count,0) AS followers_count " +
            "FROM users ui " +
            "LEFT JOIN (" +
            "    SELECT followed_id, COUNT(*) AS followers_count " +
            "    FROM follows " +
            "    GROUP BY followed_id " +
            "    ORDER BY followers_count DESC " +
            "    LIMIT #{limit}" +
            ") uf ON ui.id = uf.followed_id")
    public List<JSONObject> getMostFellowUsers(@Param("limit") int limit);
}
