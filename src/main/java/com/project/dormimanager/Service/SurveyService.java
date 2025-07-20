package com.project.dormimanager.Service;

import com.project.dormimanager.DTO.Survey;
import com.project.dormimanager.Mapper.SurveyMapper;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {
    private final SurveyMapper surveyMapper;

    public SurveyService(SurveyMapper surveyMapper) {
        this.surveyMapper = surveyMapper;
    }

    public int submitSurvey(Survey survey) {
        return surveyMapper.insertSurvey(survey);
    }
}
