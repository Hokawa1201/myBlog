package com.example.backend.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entities.Collect;
import com.example.backend.entities.CommentsLike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentLikeMapper extends BaseMapper<CommentsLike> {

}