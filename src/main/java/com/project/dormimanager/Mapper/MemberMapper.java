package com.project.dormimanager.Mapper;

import com.project.dormimanager.DTO.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    Member findByStudentId(String studentId);
    void updatePassword(@Param("studentId") String studentId, @Param("password") String password);
}

