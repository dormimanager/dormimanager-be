package com.project.dormimanager.Mapper;

import com.project.dormimanager.Vo.Test;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TestMapper {
    List<Test> selectAll();
}
