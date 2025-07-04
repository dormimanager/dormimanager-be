package com.project.dormimanager.Mapper;

import com.project.dormimanager.DTO.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<Notice> selectAllOrderByRegDateDesc();
    void increaseViews(Long id);
    Notice findById(Long id);
    int update(Notice notice);
    int insert(Notice notice);
}
