package com.zfc.study.redis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zfc.study.domain.entity.User;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-08-28 20:37
 * @T: UserRedis
 **/
@Repository
public class UserRedis {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void add(String key, Long time, User user){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key,gson.toJson(user),time, TimeUnit.MINUTES);

    }

    public void add(String key , Long time, List<User> user){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key,gson.toJson(user),time,TimeUnit.MINUTES);
    }

    public User get(String key){
        Gson gson = new Gson();
        User user = null;
        if (StringUtils.isNotBlank(key)){
            String userJson = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotBlank(userJson)){
                user = gson.fromJson(userJson,User.class);
            }
        }
        return user;
    }

    public List<User> getList(String key){
        Gson gson = new Gson();
        List<User> ts = null;
        if (StringUtils.isNotBlank(key)){
            String listJson = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotBlank(listJson)){
                ts = gson.fromJson(listJson, new TypeToken<List<User>>(){}.getType());
            }
        }
        return ts;
    }

    public void delete(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }


    public static void main(String[] args) {
        System.out.println(TimeUnit.MINUTES);
    }

}
