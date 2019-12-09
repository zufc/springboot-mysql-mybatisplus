package com.zfc.study.proxy;

import com.zfc.study.rocketmq.message.impl.UserProcessorImpl;
import com.zfc.study.service.UserService;
import com.zfc.study.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-09-05 15:10
 * @T: PerformanceHandler
 **/
public class PerformanceHandler implements InvocationHandler {
    private Object target;

    public PerformanceHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object obj = method.invoke(target,args);
        return obj;
    }

    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }


    public static void main(String[] args) {

        UserService us = new PerformanceHandler(new UserServiceImpl()).getProxy();
        us.queryList();
    }
}
