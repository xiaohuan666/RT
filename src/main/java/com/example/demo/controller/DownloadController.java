package com.example.demo.controller;

import com.example.demo.service.DownloadExcelService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

@Controller
@RequestMapping("/hehe")
public class DownloadController {
    @Autowired
    DownloadExcelService downloadExcelService;
    @RequestMapping(value = "/hehe1",produces = {"application/vnd.ms-excel;charset=UTF-8"})
    public ResponseEntity<byte[]> download(HttpServletResponse response){
//        Object value = param.getValue();
        String fileName = "userinfo.xlsx";
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes()));
        ByteArrayOutputStream byos = new ByteArrayOutputStream();
        //将实际文件写入byos流中

        byte[] content = byos.toByteArray();
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(content, httpHeaders, HttpStatus.OK);
        return responseEntity;

    }
}
