package com.zfc.study.enums;


import com.zfc.study.domain.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-08-27 10:16
 * @T: TestEnum
 **/
public class TestEnum {
    public static void main(String[] args) {

        Size nn = Size.valueOf("SMALL");
        System.out.println(nn);

        Size[] av = Size.values();
        System.out.println(av);

        for(Size s : Size.values()){
            System.out.println(s);

        }

        Class<?> cl = nn.getClass();
        System.out.println(cl.getName());
        String s = "java.util.Scanner";

        try {
            Class<?> clasz =Class.forName("UserServiceImpl");
            Method method = clasz.getMethod("insert", User.class);
            User user = new User();
            user.setAge(11);
            method.invoke(user);
          // Field field = clasz.getField("");
            assert "a".equals("");
            Objects.requireNonNull("a","must not null");

        }catch (Exception e){

        }




    }

}
