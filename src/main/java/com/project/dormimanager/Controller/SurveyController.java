package com.project.dormimanager.Controller;

import com.project.dormimanager.DTO.Survey;
import com.project.dormimanager.Service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stu/survey")
public class SurveyController {
    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public ResponseEntity<String> submitSurvey(@RequestBody Survey survey) {
        surveyService.submitSurvey(survey);
        return ResponseEntity.ok("설문 제출 완료");
    }

}
