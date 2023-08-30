package com.example.backend.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entities.Follow;
import com.example.backend.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FollowMapper extends BaseMapper<Follow> {

    @Select("SELECT users.* FROM users,follows\n" +
            "WHERE follows.follow_id=#{userId} AND users.id = follows.followed_id")
    List<User> getFollowedUsersByUserId(@Param("userId") int userId);

    @Select("SELECT users.* FROM users,follows\n" +
            "WHERE follows.followed_id=#{userId} AND users.id = follows.follow_id")
    List<User> getFansUsersByUserId(@Param("userId") int userId);
}
