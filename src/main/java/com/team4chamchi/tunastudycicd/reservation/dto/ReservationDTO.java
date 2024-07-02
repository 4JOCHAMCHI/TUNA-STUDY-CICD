package com.team4chamchi.tunastudycicd.reservation.dto;

import com.team4chamchi.tunastudycicd.reservation.aggregate.Reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private int reservationId;
    private Boolean occupied;
    private int memberId;
    private String memberPhone;
    private int roomId;
    private String roomName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ReservationDTO(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.occupied = reservation.getOccupied();
        this.memberId = reservation.getMember().getMemberId();
        this.memberPhone = reservation.getMember().getMemberPhone();
        this.roomId = reservation.getRoom().getRoomId();
        this.roomName = reservation.getRoom().getRoomName();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
    }
}
