package com.zfc.study.controller;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zfc.study.domain.entity.User;
import com.zfc.study.service.UserService;
import com.zfc.study.util.ExcelPoiUtils;
import com.zfc.study.util.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-11 19:45
 **/
@RestController
@RequestMapping("/api/v1/excel/")
@Slf4j
public class ExcelController {

    @Autowired
    private UserService userService;

    @PostMapping("importExcel")
    public String importExcel(@RequestParam("file") MultipartFile file){
        String message = null;
        User user = null;
        List<User> userList  = Lists.newArrayList();
        try {
            List<String[]> list = ExcelPoiUtils.readExcel(file.getInputStream());
            for(int i = 1; i < list.size(); i++){
                String[] cellList = list.get(i);
                user = new User();
                user.setId(cellList[0]);
                user.setUserId(cellList[1]);
                user.setUserName(cellList[2]);
                user.setPassword(cellList[3]);
                user.setAge(Integer.valueOf(cellList[4]));

                log.info("从Excel导入数据到数据库的详细为：{}", JSONObject.toJSONString(user));
                userList.add(user);
            }

            int result = userService.insertList(userList);
            log.info("共{}条数据,导入成功{}条数据",userList.size(),result);
            message =  "共"+userList.size()+"条数据,导入成功"+result+"条数据";
        }/*catch (IOException ioe){
            logger.warn(ioe.getMessage());
        }*/catch (Exception e){
            log.warn(e.getMessage());
        }


        return message;
    }




}
