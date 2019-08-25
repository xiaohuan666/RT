package com.example.demo.service;

import com.example.demo.dao.NeInfoDao;
import com.example.demo.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;
    @Autowired
    NeInfoDao neInfoDao;

}
