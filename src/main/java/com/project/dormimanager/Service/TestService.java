package com.project.dormimanager.Service;

import com.project.dormimanager.Mapper.TestMapper;
import com.project.dormimanager.Vo.Test;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestService {
    private final TestMapper testMapper;

    public TestService(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    public List<Test> getAllTests() {
        return testMapper.selectAll();
    }
}
