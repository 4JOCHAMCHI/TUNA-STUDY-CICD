package com.team4chamchi.tunastudycicd.reservation.aggregate;

import com.team4chamchi.tunastudycicd.member.aggregate.Member;
import com.team4chamchi.tunastudycicd.reservation.dto.ReservationDTO;
import com.team4chamchi.tunastudycicd.studyroom.aggregate.StudyRoom;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int reservationId;

    @Column(name = "occupied")
    private Boolean occupied;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private StudyRoom room;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    public Reservation(Member member, StudyRoom room) {
        this.occupied = false;
        this.member = member;
        this.room = room;
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now();
    }
}