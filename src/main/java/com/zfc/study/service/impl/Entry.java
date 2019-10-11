package com.zfc.study.service.impl;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-08-27 11:05
 * @T: Entry
 **/
public class Entry<K,V> {

    private K key;
    private V value;

    public Entry(K key,V value){
        this.key = key;
        this.value = value;
    }

    public K getKey(){ return key;}

    public V getValue(){ return value;}

}
