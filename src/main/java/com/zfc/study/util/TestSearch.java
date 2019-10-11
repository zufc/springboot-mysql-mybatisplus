package com.zfc.study.util;

import com.baomidou.mybatisplus.extension.api.R;
import com.zfc.study.SpringbootMysqlMybatisplusApplication;
import com.zfc.study.domain.entity.User;
import com.zfc.study.service.impl.UserServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.support.ServletContextResource;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-08-27 15:42
 * @T: TestSearch
 **/
public class TestSearch {


    public static void  rs() throws Exception{
  /*      Resource baResource = new ByteArrayResource(new byte[1]);
        Resource classResource = new ClassPathResource("");
        Resource fssResource = new FileSystemResource("");
        Resource urlResource = new UrlResource("");
        Resource pahResource = new PathResource("");
        //Resource servletContextResource = new ServletContextResource(ac,"");
        ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();
        Resource[] resource  = rpr.getResources("classpath*:");

        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xbdReader = new XmlBeanDefinitionReader(bf);
        xbdReader.loadBeanDefinitions(resource);*/

    }


    public static User initByDefaultConst() throws  Throwable{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class clazz = classLoader.loadClass("com.zfc.study.domain.entity.User");

        Constructor cons = clazz.getDeclaredConstructor((Class[])null);
        User user = (User) cons.newInstance();

        Method setUserId = clazz.getMethod("setUserId",String.class);
        setUserId.invoke(user,"q3");
        return user;
    }


    public static void main(String[] args) throws Throwable {
        User user = TestSearch.initByDefaultConst();
        System.out.println(user);
        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
       // int i = biSearch(array,4);
       // System.out.println(i);

        int[] a = {1,8,2,5,7,3,4,9,0,6};
        bubbleSort(a,a.length-1);
        System.out.println(Arrays.toString(a));
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:beans.xml");
        User user1 = (User) ctx.getBean("user");
        user1.setPassword("1342125");
        System.out.println(user1);
        System.out.println("=========================================");
        String pattern = "{0},你好！你于{1}在工商银行存入{2}元。";
        Object[] params = {"John",new GregorianCalendar().getTime(),1.0E3};
        String msg = MessageFormat.format(pattern,params);
        System.out.println(msg);


    }


    /**
     * @Author zufeichao
     * @Description 二分法 又叫折半查找，要求待查找的序列有序。
     * @Date 16:01 2019/8/27
     * @Param [array, a]
     * @return int
     **/
    public static int biSearch(int[] array,int a){
        int lo = 0;
        int hi = array.length -1;
        int mid;
        int count = 0;
        while(lo <= hi){
            count ++;
            mid = (lo + hi)/2;
            if(array[mid] == a){
                System.out.println("最终次数：" + count);
                return mid + 1;
            }else if(array[mid] < a){
                lo = mid + 1;
            }else{
                hi = mid -1 ;
            }

        }

        return -1;
    }

    /**
     * @Author zufeichao
     * @Description 冒泡排序法 比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
     * @Date 16:26 2019/8/27
     * @Param [a, n]
     * @return void
     **/
    public static void bubbleSort(int[] a,int n ){
        int i,j;
        for ( i = 0 ; i < n ;i++){
            for (j = 1; j< n-i; j++){
                if(a[j-1] > a[j]){
                    int temp ;
                    temp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = temp;
                }
            }
        }

    }
}
