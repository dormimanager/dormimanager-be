package com.project.dormimanager.Controller;

import com.project.dormimanager.DTO.Complain;
import com.project.dormimanager.DTO.Notice;
import com.project.dormimanager.Mapper.ComplainMapper;
import com.project.dormimanager.Service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/complain")
public class ComplainController {

    @Autowired
    private ComplainService complainService;

    @Autowired
    private ComplainMapper complainMapper;


    @GetMapping("/get")
    public List<Complain> getNotices() {
        return complainService.getComplains();
    }


    @GetMapping("/{id}")
    public Complain getNoticeById(@PathVariable Long id) {
        return complainMapper.findById(id);
    }

    @PutMapping("/edit/{id}")
    public int updateNotice(@PathVariable Long id, @RequestBody Complain complain) {
        complain.setId(id);
        return complainMapper.update(complain);
    }

    @PostMapping("/save")
    public int createNotice(@RequestBody Complain complain) {
        return complainMapper.insert(complain);
    }
}
