package com.project.dormimanager.Service;

import com.project.dormimanager.DTO.Complain;
import com.project.dormimanager.Mapper.ComplainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplainService {

    @Autowired
    private ComplainMapper complainMapper;

    public List<Complain> getComplains() {
        System.out.println(complainMapper.selectAllOrderByRegDateDesc());
        return complainMapper.selectAllOrderByRegDateDesc();
    }
}