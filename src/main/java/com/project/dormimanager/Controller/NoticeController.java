package com.project.dormimanager.Controller;

import com.project.dormimanager.DTO.Notice;
import com.project.dormimanager.Mapper.NoticeMapper;
import com.project.dormimanager.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeMapper noticeMapper;


    @GetMapping("/get")
    public List<Notice> getNotices() {
        return noticeService.getNotices();
    }

    @PostMapping("/increaseView")
    public void increaseView(@RequestBody Map<String, Object> body) {
        Long id = Long.parseLong(body.get("id").toString());
        noticeService.increaseViews(id);
    }

    @GetMapping("/{id}")
    public Notice getNoticeById(@PathVariable Long id) {
        return noticeMapper.findById(id);
    }

    @PutMapping("/edit/{id}")
    public int updateNotice(@PathVariable Long id, @RequestBody Notice notice) {
        notice.setId(id);
        return noticeMapper.update(notice);
    }

    @PostMapping("/save")
    public int createNotice(@RequestBody Notice notice) {
        return noticeMapper.insert(notice);
    }
}
