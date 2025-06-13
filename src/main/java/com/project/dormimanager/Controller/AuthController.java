package com.project.dormimanager.Controller;

import com.project.dormimanager.DTO.LoginRequest;
import com.project.dormimanager.DTO.RegisterRequest;
import com.project.dormimanager.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.authenticate(loginRequest.getStudentId(), loginRequest.getPassword());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 예외 메시지에 따라 상태 코드와 메시지를 반환
            if (e.getMessage().equals("1")) {
                return ResponseEntity.status(401).body("학번이 틀렸습니다.");
            } else if (e.getMessage().equals("2")) {
                return ResponseEntity.status(401).body("비밀번호가 틀렸습니다.");
            } else {
                return ResponseEntity.status(500).body("로그인 중 오류가 발생했습니다.");
            }
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            authService.register(registerRequest);
            return ResponseEntity.ok("회원가입 성공");
        } catch (Exception e) {
            if (e.getMessage().equals("1")) {
                return ResponseEntity.status(401).body("등록된 학번이 없습니다.");
            } else if (e.getMessage().equals("2")) {
                return ResponseEntity.status(401).body("이미 가입된 학번 입니다.");
            } else {
                return ResponseEntity.status(500).body("회원가입 중 오류가 발생했습니다.");
            }
        }
    }
}

