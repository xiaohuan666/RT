package com.example.demo;

import com.example.demo.dao.StudentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    StudentDao studentDao;
    @Test
    public void contextLoads() {
        studentDao.getStudents();
        System.out.println("hehe");
    }

}
