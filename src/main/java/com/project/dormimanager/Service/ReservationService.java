package com.project.dormimanager.Service;

import com.project.dormimanager.DTO.Reservation;
import com.project.dormimanager.Mapper.ReservationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReservationService {
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    @Transactional
    public boolean reserveLaundry(Reservation reservation) {
        // studyroomId는 세탁기 예약이므로 null로
//        reservation.setStudyroomId(null);
        // reservType은 "세탁기"로 고정
//        reservation.setReservType("세탁기");
        // laundryId, buildingId 등은 프론트에서 전달받음

        // **insert 전에 로그로 값 확인**
        System.out.println("=== Reservation Insert Data ===");
        System.out.println("studentId: " + reservation.getStudentId());
        System.out.println("reservType: " + reservation.getReservType());
        System.out.println("laundryId: " + reservation.getLaundryId());
        System.out.println("reservTime: " + reservation.getReservTime());
        System.out.println("buildingId: " + reservation.getBuildingId());
        System.out.println("studyroomId: " + reservation.getStudyroomId());
        System.out.println("===============================");

        return reservationMapper.insertReservation(reservation) > 0;
    }

    public List<String> getReservedTimes(int laundryId, String date) {
        return reservationMapper.selectReservedTimes(laundryId, date);
    }

    public List<String> getReservedTimes(int laundryId, String date, String reservType) {
        // 1. DB에서 해당 날짜, 기기, 타입의 예약 시간(reserv_time) 리스트 조회
        List<Date> reservTimes = reservationMapper.selectReservedTimes(laundryId, date, reservType);

        // 2. 시간대별 타임 매핑 테이블 정의
        List<TimeSlot> timeSlots = Arrays.asList(
                new TimeSlot("1", LocalTime.of(8, 0), LocalTime.of(9, 30)),
                new TimeSlot("2", LocalTime.of(9, 30), LocalTime.of(11, 0)),
                new TimeSlot("3", LocalTime.of(11, 0), LocalTime.of(12, 30)),
                new TimeSlot("4", LocalTime.of(12, 30), LocalTime.of(14, 0)),
                new TimeSlot("5", LocalTime.of(14, 0), LocalTime.of(15, 30)),
                new TimeSlot("6", LocalTime.of(15, 30), LocalTime.of(17, 0)),
                new TimeSlot("7", LocalTime.of(17, 0), LocalTime.of(18, 30)),
                new TimeSlot("8", LocalTime.of(18, 30), LocalTime.of(20, 0)),
                new TimeSlot("9", LocalTime.of(20, 0), LocalTime.of(21, 30))
        );

        Set<String> reservedTimeSlots = new HashSet<>();
        for (Date reservTime : reservTimes) {
            LocalTime time = reservTime.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
            for (TimeSlot slot : timeSlots) {
                if (!time.isBefore(slot.start) && time.isBefore(slot.end)) {
                    reservedTimeSlots.add(slot.value);
                    break;
                }
            }
        }
        return new ArrayList<>(reservedTimeSlots);
    }

    // 시간대 구간을 표현하는 내부 클래스
    static class TimeSlot {
        String value;
        LocalTime start;
        LocalTime end;
        TimeSlot(String value, LocalTime start, LocalTime end) {
            this.value = value;
            this.start = start;
            this.end = end;
        }
    }
}
