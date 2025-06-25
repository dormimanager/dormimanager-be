package com.project.dormimanager.Controller;

import com.project.dormimanager.DTO.LoginRequest;
import com.project.dormimanager.DTO.Member;
import com.project.dormimanager.DTO.RegisterRequest;
import com.project.dormimanager.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @GetMapping("/getInfo")
    public ResponseEntity<Member> findByStudentId(@RequestParam String studentId) {
        Member member = authService.findByStudentId(studentId);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(member);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMember(
            @RequestParam String studentId,
            @RequestParam(required = false) MultipartFile profileImage,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone) throws IOException {

        String uploadDir = "C:/uploads/";
        File uploadPath = new File(uploadDir);

        // 기존 이미지 파일 삭제 (학번_profile.* 패턴)
        if(profileImage!=null){
            File[] oldFiles = uploadPath.listFiles((dir, name) ->
                    name.startsWith(studentId + "_profile.")
            );
            if (oldFiles != null) {
                for (File file : oldFiles) {
                    file.delete();
                }
            }
        }

        // 새 이미지 저장
        String imgUrl = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            String extension = profileImage.getOriginalFilename()
                    .substring(profileImage.getOriginalFilename().lastIndexOf("."));
            String fileName = studentId + "_profile" + extension;
            if (!uploadPath.exists()) uploadPath.mkdirs();
            File dest = new File(uploadPath, fileName);
            profileImage.transferTo(dest);
            imgUrl = "/uploads/" + fileName;
        }

        // 회원 정보 업데이트
        authService.updateMember(studentId, email, phone, imgUrl);

        return ResponseEntity.ok("수정되었습니다.");
    }

}

