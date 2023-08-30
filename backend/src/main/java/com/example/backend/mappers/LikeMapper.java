package com.example.backend.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entities.Like;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper extends BaseMapper<Like> {
}