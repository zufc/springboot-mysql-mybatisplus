package com.zfc.study.rocketmq.message.impl;

import com.zfc.study.domain.entity.User;
import com.zfc.study.rocketmq.message.MessageProcessor;
import com.zfc.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-12 14:48
 **/
@Service
@Slf4j
public class UserProcessorImpl implements MessageProcessor<User> {

    @Autowired
    private UserService userService;

    @Override
    public boolean handleMessage(User user) {
        log.info("user receive : {}",user);
        //int result = userService.insert(user);
        return true;
    }

    @Override
    public Class<User> getClazz() {
        return User.class;
    }

}
