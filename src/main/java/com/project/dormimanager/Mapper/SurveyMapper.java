package com.project.dormimanager.Mapper;

import com.project.dormimanager.DTO.Survey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SurveyMapper {
    int insertSurvey(Survey survey);
}
