package com.neko.seed.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neko.seed.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}