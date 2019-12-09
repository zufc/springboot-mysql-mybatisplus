package com.zfc.study.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-12-05 15:02
 * @T: TestCyclicBarrier
 **/
public class TestCyclicBarrier {
    public static void main(String[] args) {
        MainTask mainTask = new MainTask();
        CyclicBarrier cb = new CyclicBarrier(7,mainTask);
        new SubTask("a",cb).start();
        new SubTask("b",cb).start();
        new SubTask("c",cb).start();
        new SubTask("d",cb).start();
        new SubTask("e",cb).start();
        new SubTask("f",cb).start();
        new SubTask("g",cb).start();


    }
}

class MainTask implements Runnable{

    @Override
    public void run() {
        System.out.println("主任务执行。。。");
    }
}

class SubTask extends  Thread{
    private String name;
    private CyclicBarrier cb;
    SubTask(String name,CyclicBarrier cb){
        this.name = name;
        this.cb = cb;
    }

    public void run(){
        System.out.println("子任务【"+name+"】开始执行");
        for(int i=0;i<100000;i++);
        System.out.println("子任务【"+name+"】开始执行完了，并通知障碍器已完成！");

        try{
            cb.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (BrokenBarrierException e){
            e.printStackTrace();
        }


    }

}