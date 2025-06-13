package com.project.dormimanager.Service;

import com.project.dormimanager.DTO.Member;
import com.project.dormimanager.DTO.RegisterRequest;
import com.project.dormimanager.Mapper.MemberMapper;
import com.project.dormimanager.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String authenticate(Long studentId, String password) {
        Member member = memberMapper.findByStudentId(studentId);
//        if (member == null || !passwordEncoder.matches(password, member.getPassword())) {
//            throw new RuntimeException("학번 또는 비밀번호가 틀렸습니다.");
//        }
        if(member == null){
            throw new RuntimeException("1");
        }
        else if(!passwordEncoder.matches(password, member.getPassword())){
            throw new RuntimeException("2");
        }
        return jwtTokenProvider.createToken(member.getStudentId().toString(), member.getRole());
    }

    public void register(RegisterRequest request) {
        // 중복 학번 체크 등 추가 가능
        Member member = memberMapper.findByStudentId(request.getStudentId());
        if(member == null){
            throw new RuntimeException("1");
        }
        else{
            if(member.getPassword()!=null){
                throw new RuntimeException("2");
            }
            else if (request.getEmail().equals(member.getEmail()) &&
                    request.getName().equals(member.getName()) &&
                    request.getGender().equals(member.getGender()) &&
                    request.getPhone().equals(member.getPhone()) &&
                    request.getStudentId().equals(member.getStudentId()))
            {
                memberMapper.updatePassword(request.getStudentId(), passwordEncoder.encode(request.getPassword()));
            }
            else{
                throw new RuntimeException("3");
            }
        }
    }
}
