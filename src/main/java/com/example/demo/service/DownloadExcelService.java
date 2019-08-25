package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.io.File;


public interface DownloadExcelService {
    public File getFile(String houZhui);
}
