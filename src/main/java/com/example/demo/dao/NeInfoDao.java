package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NeInfoDao {
    List<Map<String,Object>> getUserNeList(Map<String,Object> userInfo);
}
