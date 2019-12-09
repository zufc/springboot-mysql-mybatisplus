package com.zfc.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-12-05 14:34
 * @T: TestLock
 **/
public class TestLock {

    public static void main(String[] args) {
        MyCount myCount = new MyCount("621558000000000000000000",10000);

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,60,
                TimeUnit.MICROSECONDS,new ArrayBlockingQueue<>(3));

        Thread t1 = new SaveThread("张三",myCount,2000);
        Thread t2 = new SaveThread("李四",myCount,3600);
        Thread t3 = new DrawThread("王五",myCount,2700);
        Thread t4 = new SaveThread("老张",myCount,600);
        Thread t5 = new DrawThread("老牛",myCount,1300);
        Thread t6 = new DrawThread("阿胖",myCount,800);

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);

        pool.shutdown();

    }


}

class SaveThread extends Thread{
    private String name;
    private MyCount myCount;
    private int x;

    SaveThread(String name,MyCount myCount,int x){
        this.name = name;
        this.myCount = myCount;
        this.x = x;
    }

    public void run(){
        myCount.saving(x,name);
    }
}


class DrawThread extends Thread{
        private String name;//操作人  
        private MyCount myCount;//账户  
        private int x;//存款金额  
        DrawThread(String name,MyCount myCount,int x){
            this.name=name;
            this.myCount=myCount;
            this.x=x;
        }
        public void run(){
            myCount.drawing(x,name);
        }
}


class MyCount{
    private String oid;
    private int cash;
    private Lock lock = new ReentrantLock();
    private Condition save = lock.newCondition();
    private Condition draw = lock.newCondition();

    MyCount(String oid,int cash){
        this.oid = oid;
        this.cash = cash;
    }

    public void saving(int x,String name){
        lock.lock();
        if (x > 0){
            cash += x;
            System.out.println(name +"存款"+x+"，当前余额为："+cash);
        }
        draw.signalAll();
        lock.unlock();
    }

    public void drawing(int x,String name){
        lock.lock();
        try{
           if (cash -x < 0){
               draw.await();
           }  else {
               cash -= x;
               System.out.println(name+"取款"+x+",当前余额为"+cash);
           }
           save.signalAll();
        } catch (InterruptedException e){
           e.printStackTrace();
        }finally {
           lock.unlock();
        }


    }


}
