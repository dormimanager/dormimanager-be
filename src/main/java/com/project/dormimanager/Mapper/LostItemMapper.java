package com.project.dormimanager.Mapper;

import com.project.dormimanager.DTO.LostItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LostItemMapper {
    void insertLostItem(LostItem dto);

    List<LostItem> findItems(
            @Param("category") String category,
            @Param("startDate") String startDate,  // yyyy-MM-dd 형태 문자열
            @Param("endDate") String endDate,
            @Param("owner") String owner,
            @Param("itemName") String itemName
    );
}