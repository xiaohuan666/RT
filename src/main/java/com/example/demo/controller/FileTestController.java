package com.example.demo.controller;

import com.example.demo.Pojo.response.ResultInfo;
import com.example.demo.service.DownloadExcelService;
import com.example.demo.service.FileTestService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.compiler.MemberResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;


@Controller
@RequestMapping("/fileJq")
public class FileTestController {
    @Autowired
    FileTestService fileTestService;
    @RequestMapping(value = "/exportFile",method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(){

        String fileName = "userinfo.xlsx";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes()));
        ByteArrayOutputStream byos = new ByteArrayOutputStream();
        //将实际文件写入byos流中

        byte[] content = byos.toByteArray();
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(content, httpHeaders, HttpStatus.OK);
        return responseEntity;

    }

    @PostMapping("uploadFile")
    public ResultInfo uploadFile(@Param("file")MultipartFile file){
        String filename = file.getOriginalFilename();
        int i = 0;
        try {
            i = filename.lastIndexOf(".");
        } catch (NullPointerException e) {
            e.printStackTrace();
            return ResultInfo.errorMessage("上传文件非法,无法解析");
        }
        String suff = filename.substring(i + 1);
        if (suff.equalsIgnoreCase("xlsx")||suff.equalsIgnoreCase("csv")){
            return fileTestService.parseFile(file,suff);
        }else {
            return ResultInfo.success();

        }
    }

}
