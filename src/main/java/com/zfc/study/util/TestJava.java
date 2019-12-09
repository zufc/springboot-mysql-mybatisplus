package com.zfc.study.util;

import com.zfc.study.domain.entity.User;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-11-15 9:47
 * @T: TestJava
 **/
public class TestJava {

    public static void main(String[] args) throws Exception {

        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(3);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,2,10,
                TimeUnit.SECONDS,blockingQueue);

        Callable c1 = new MyCallable("A");
        Callable c2 = new MyCallable("B");

        Future f1 = executor.submit(c1);
        Future f2 = executor.submit(c2);

        System.out.println(">>>"+f1.get().toString());

        System.out.println(">>>"+f2.get().toString());

        executor.shutdown();

        BlockingQueue<Runnable> bq = new ArrayBlockingQueue<>(20);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,5,200,TimeUnit.MILLISECONDS,bq);
        Thread t1 = new MyThread("t1");

        Thread t2 = new MyThread("t2");
        Thread t3 = new MyThread("t3");
        Thread t4 = new MyThread("t4");
        Thread t5 = new MyThread("t5");
        Thread t6 = new MyThread("t6");
        Thread t7 = new MyThread("t7");
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        pool.execute(t7);
        pool.shutdown();










    }
}

class MyThread extends Thread{

    private String myname;

    MyThread(String name){
        super(name);
    }


    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    @Override
    public void run(){
        Thread t = Thread.currentThread();
        System.out.println(t.getName());
        System.out.println(Thread.currentThread().getName() +"正在执行。。。"+getMyname());
        try{
            Thread.sleep(100L);

        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

}

class MyCallable implements Callable{

    private String oid;

    MyCallable(String oid){
        this.oid = oid;
    }

    @Override
    public Object call() throws Exception {
        return oid + "任务返回的内容";
    }
}

