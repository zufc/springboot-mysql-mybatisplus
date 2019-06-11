package com.zfc.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfc.study.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper extends BaseMapper<User> {



}
