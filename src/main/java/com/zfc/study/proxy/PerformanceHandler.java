package com.zfc.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
}
