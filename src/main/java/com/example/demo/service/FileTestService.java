package com.example.demo.service;

import com.example.demo.Pojo.response.ResultInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileTestService {

    ResultInfo parseFile(MultipartFile file, String suff) throws IOException;
}
