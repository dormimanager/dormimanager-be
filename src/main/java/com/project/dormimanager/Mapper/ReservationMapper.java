package com.project.dormimanager.Mapper;

import com.project.dormimanager.DTO.Reservation;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ReservationMapper {
    int insertReservation(Reservation reservation);
    List<String> selectReservedTimes(@Param("laundryId") int laundryId, @Param("date") String date);

    List<Date> selectReservedTimes(@Param("laundryId") int laundryId,
                                   @Param("date") String date,
                                   @Param("reservType") String reservType);

}
