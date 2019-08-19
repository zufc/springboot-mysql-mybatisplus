package com.zfc.study.thread;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.LinkedList;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description
 * wait()用在以下场合:
 * （1）当缓冲区满时，缓冲区调用wait()方法，使得生产者释放锁，当前线程阻塞，其他线程可以获得锁。
 *
 * （2）当缓冲区空时，缓冲区调用wait()方法，使得消费者释放锁，当前线程阻塞，其他线程可以获得锁。
 *
 * notify()用在以下场合：
 * （1）当缓冲区未满时，生产者生产商品放入缓冲区，然后缓冲区调用notify()方法，
 * 通知上一个因wait()方法释放锁的线程现在可以去获得锁了，同步块代码执行完成后，
 * 释放对象锁，此处的对象锁，锁住的是缓冲区。
 *
 * （2）当缓冲区不为空时，消费者从缓冲区中取出商品，然后缓冲区调用notify()方法，
 * 通知上一个因wait()方法释放锁的线程现在可以去获得锁了，同步块代码执行完成后，释放对象锁。
 * ---------------------
 *
 * @Date 2019-08-16 11:33
 * @T: ProducerAndConsumer
 **/
public class ProducerAndConsumer {
    //最大容量
    public static final int MAX_SIZE = 5;
    //存储媒介
    public static LinkedList<Integer> list = new LinkedList<>();

    class Producer implements Runnable{

        @Override
        public void run() {
            synchronized (list){
                //当仓库容量已经达到最大值
                while(list.size() == MAX_SIZE){
                    System.out.println("仓库已满，生产者-" + Thread.currentThread().getName() + ",不可生产。");
                    try {
                        System.out.println("生产者-"+Thread.currentThread().getName() +"，等待生产");
                        list.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                list.add(1);
                System.out.println("生产者-" + Thread.currentThread().getName() +"生产，仓库容量为" + list.size() );
                list.notify();

            }

        }
    }


    class Consumer implements Runnable{

        @Override
        public void run() {
            synchronized (list){
                while (list.size() == 0){
                    System.out.println("仓库为空，消费者-" + Thread.currentThread().getName() + ",不可消费。");

                    try {
                        System.out.println("消费者-"+Thread.currentThread().getName() +"，等待消费");
                        list.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                list.removeFirst();
                System.out.println("消费者-" + Thread.currentThread().getName() +"消费，仓库容量为" + list.size() );
                list.notify();
            }
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumer pac = new ProducerAndConsumer();
        Producer producer = pac.new Producer();
        Consumer consumer = pac.new Consumer();
        for (int i = 0 ; i < 20; i++){
            Thread thread1 = new Thread(producer);
            thread1.start();
            thread1.setName("线程-"+i);
            Thread thread2 = new Thread(consumer);
            thread2.start();
            thread2.setName("线程-"+i);

        }
    }


}
