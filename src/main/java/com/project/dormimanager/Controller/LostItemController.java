package com.project.dormimanager.Controller;

import com.project.dormimanager.DTO.LostItem;
import com.project.dormimanager.Service.LostItemService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/lost-items")
public class LostItemController {

    private final LostItemService lostItemService;

    public LostItemController(LostItemService lostItemService) {
        this.lostItemService = lostItemService;
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createLostItem(
            @RequestParam("image") MultipartFile image,
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("description") String description,
            @RequestParam("foundDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date foundDate,
            @RequestParam("foundLocation") String foundLocation) throws IOException {

        LostItem dto = new LostItem();
        dto.setItemName(name);
        dto.setCategory(category);
        dto.setDescription(description);
        dto.setFoundDate(foundDate);
        dto.setFoundLocation(foundLocation);

        // 이미지 파일 처리 (예: 서버 디스크 저장 또는 DB 저장)
        String imageUrl = lostItemService.storeImage(image);
        dto.setImageUrl(imageUrl);

        lostItemService.saveLostItem(dto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public List<LostItem> getLostItems(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String owner,
            @RequestParam(required = false) String itemName) {

        // 모두 null/""이면 전체조회, 아니면 조건검색
        return lostItemService.findItems(category, startDate, endDate, owner, itemName);
    }
}
