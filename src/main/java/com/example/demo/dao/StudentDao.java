package com.example.demo.dao;

import com.example.demo.Pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface StudentDao {
    List<Student> getStudents();
}
