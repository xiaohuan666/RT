package com.example.demo.service.impl;

import com.example.demo.Pojo.response.ResultInfo;
import com.example.demo.service.FileTestService;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileTestServiceImpl implements FileTestService {

    @Value("${temp.path}")
    String tempPath ;
    @Override
    public ResultInfo parseFile(MultipartFile file, String suff) throws IOException {
        String tempDirPath = makeTempDir();
        String filePath = tempDirPath + "UserInfo_Jq" + Math.random() * 100 + "." + "suff";
        File file1 = new File(filePath);
        FileUtils.touch(file1);
        FileUtils.copyInputStreamToFile(file.getInputStream(),file1);


        return ResultInfo.success();
    }

    private String makeTempDir(){
        String tempDirPath = tempPath+"tempDir_"+new SimpleDateFormat("yyyyMMdd").format(new Date());
        File file = new File(tempDirPath);
        if (!file.exists()){
            file.mkdirs();
        }
        return tempDirPath;
    }
}
