package com.zfc.study.controller;

import com.zfc.study.util.PdfUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-10-30 21:00
 * @T: ReportController
 **/
@RestController
@RequestMapping(value = "/report")
public class ReportController {


    @RequestMapping(value = "/downLoad/pdf")
    public void downLoadPDF(HttpServletResponse response) {
        String fileName = "企业基本情况登记表.pdf";
        OutputStream outputStream = null;
        try {
            // 防止中文乱码
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            //生成pdf文件
           // TestModel.createBusinessInfoPDF(outputStream);
            PdfUtil.createPdf(fileName,outputStream);
            outputStream.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
