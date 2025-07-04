package com.project.dormimanager.Mapper;

import com.project.dormimanager.DTO.Complain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComplainMapper {
    List<Complain> selectAllOrderByRegDateDesc();
    Complain findById(Long id);
    int update(Complain complain);
    int insert(Complain complain);
}
