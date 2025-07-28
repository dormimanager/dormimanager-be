package com.project.dormimanager.Service;

import com.project.dormimanager.DTO.LostItem;
import com.project.dormimanager.Mapper.LostItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class LostItemService {

    private final LostItemMapper lostItemMapper;

    // 이미지 저장 위치 설정 (예시)
    private final String uploadDir = "C:/uploads//lost-items/";

    public LostItemService(LostItemMapper lostItemMapper) {
        this.lostItemMapper = lostItemMapper;
    }

    public String storeImage(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String filename = UUID.randomUUID().toString() + ext;

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(filename);
        multipartFile.transferTo(filePath.toFile());

        // 실제 서비스에선 이미지 URL 주소 규칙에 맞게 반환
        return "/uploads/lost-items/" + filename;
    }

    public void saveLostItem(LostItem dto) {
        lostItemMapper.insertLostItem(dto);
    }

    public List<LostItem> findItems(String category, String startDate, String endDate, String owner, String itemName) {
        return lostItemMapper.findItems(category, startDate, endDate, owner, itemName);
    }
}
