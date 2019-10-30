package com.zfc.study.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;


/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-10-22 14:22
 * @T: InsertSql
 **/

public class InsertSql {


    public static void main(String[] args) throws FileNotFoundException {
        List<List<String>> list=new ArrayList<>();
        //String ss="INSERT INTO `mall_category` VALUES ({{id}}, 123456, {{level}}, {{code}}, {{name}},{{desc}}, {{parent_id}}, null, {{sort}}, {{isLeaf}}, 'c425517a7990421c85f8e37743239c66', '贝朗医疗（上海）国际贸易有限公司', 'showyu', '1176779047980630057', 'beilang1', '2019-10-12 21:18:48', '1176779047980630057', 'beilang1', '2019-10-12 21:18:48', 0);\n";

        String ss = "INSERT INTO `mall_class` VALUES ({{id}}, {{code}}, {{name}}, {{parent_id}}, {{sort}},{{desc}}, {{isLeaf}}, {{level}}, 'showyu', '1176779047980630057', 'beilang1', '2019-10-12 21:18:48', '1176779047980630057', 'beilang1', '2019-10-12 21:18:48', 0);\n";

        try {
            FileReader fr = new FileReader("C:\\Users\\11190\\Desktop\\贝朗物料信息.txt");
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                list.add(Arrays.asList(str.split(",")));
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> list1=new ArrayList<>();
        List<Integer> longs=new ArrayList<>();
        for (int i=202001;i<list.size()*2+2022001;i++) {
            longs.add(i);
        }
        List<Integer> integers=new ArrayList<>();
        for (int i=2000;i<list.size()*2+2000;i++){
            integers.add(i);
        }
        list.stream().collect(groupingBy(e->e.get(0),groupingBy(f->f.get(1),groupingBy(g->g.get(2)))))
                .forEach((k,v)->{
                    String pid1=String.valueOf(longs.get(0));
                    String str1=ss.replace("{{level}}","1")
                            .replace("{{name}}","\'"+k+"\'")
                            .replace("{{desc}}","\'一级类目\'")
                            .replace("{{sort}}","1")
                            .replace("{{isLeaf}}","0")
                            .replace("{{code}}",String.valueOf(integers.get(0)))
                            .replace("{{id}}",pid1)
                            .replace("{{parent_id}}","0");
                    longs.remove(0);
                    integers.remove(0);
                    list1.add(str1);
                    List<Integer> sortList=new ArrayList<>();
                    for (int i=0;i<v.size();i++){
                        sortList.add(i+1);
                    }
                    v.forEach((e,f)->{

                        String pid2=String.valueOf(longs.get(0));
                        String str2=ss.replace("{{level}}","2")
                                .replace("{{name}}","\'"+e+"\'")
                                .replace("{{desc}}","\'二级类目\'")
                                .replace("{{sort}}",String.valueOf(sortList.get(0)))
                                .replace("{{isLeaf}}","0")
                                .replace("{{id}}",pid2)
                                .replace("{{code}}",String.valueOf(integers.get(0)))
                                .replace("{{parent_id}}",pid1);
                        longs.remove(0);
                        integers.remove(0);
                        list1.add(str2);
                        List<Integer> list2=new ArrayList<>();
                        for (int i=0;i<f.size();i++){
                            list2.add(i+1);
                        }
                        f.forEach((m,n)->{

                            String str3=ss.replace("{{level}}","3")
                                    .replace("{{name}}","\'"+m+"\'")
                                    .replace("{{desc}}","\'三级类目\'")
                                    .replace("{{sort}}",String.valueOf(list2.get(0)))
                                    .replace("{{isLeaf}}","1")
                                    .replace("{{id}}",String.valueOf(longs.get(0)))
                                    .replace("{{code}}",String.valueOf(integers.get(0)))
                                    .replace("{{parent_id}}",pid2);
                            list2.remove(0);
                            longs.remove(0);
                            integers.remove(0);
                            list1.add(str3);
                        });
                    });
                });
        try {
            FileWriter fw=new FileWriter("C:\\Users\\11190\\Desktop\\贝朗物料信息SQL1.txt");
            for (String s:list1){
                fw.write(s);
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
