package com.project.dormimanager.Controller;

import com.project.dormimanager.DTO.Reservation;
import com.project.dormimanager.Service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stu/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/laundry")
    public ResponseEntity<?> reserveLaundry(@RequestBody Reservation reservation,
                                            @RequestHeader("studentId") String studentId) {
        // studentId는 인증 토큰에서 추출하거나, 헤더 등에서 받음
        reservation.setStudentId(studentId);

        // 1. payload가 백엔드에 도착하자마자 로그 찍기
        System.out.println("=== [Controller] Payload Received ===");
        System.out.println("studentId: " + reservation.getStudentId());
        System.out.println("reservType: " + reservation.getReservType());
        System.out.println("laundryId: " + reservation.getLaundryId());
        System.out.println("reservTime: " + reservation.getReservTime());
        System.out.println("buildingId: " + reservation.getBuildingId());
        System.out.println("studyroomId: " + reservation.getStudyroomId());
        System.out.println("====================================");

        boolean result = reservationService.reserveLaundry(reservation);
        if (result) {
            return ResponseEntity.ok("예약 성공");
        } else {
            return ResponseEntity.badRequest().body("예약 실패");
        }
    }

    @GetMapping("/laundry/times")
    public ResponseEntity<?> getReservedTimes(
            @RequestParam int laundryId,
            @RequestParam String date, // "yyyy-MM-dd"
            @RequestParam String reservType // "세탁기" or "건조기"
    ) {
        // 예약된 시간대 리스트 조회
        return ResponseEntity.ok(reservationService.getReservedTimes(laundryId, date, reservType));
    }

}
