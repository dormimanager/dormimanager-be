package com.project.dormimanager.Controller;


import com.project.dormimanager.Vo.Test;
import com.project.dormimanager.Service.TestService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public List<Test> getTests() {
        System.out.println(testService.getAllTests());
        return testService.getAllTests();
    }
}
