package com.project.dormimanager.Service;

import com.project.dormimanager.DTO.Notice;
import com.project.dormimanager.Mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public List<Notice> getNotices() {
        System.out.println(noticeMapper.selectAllOrderByRegDateDesc());
        return noticeMapper.selectAllOrderByRegDateDesc();
    }

    public void increaseViews(Long id) {
        noticeMapper.increaseViews(id);
    }
}