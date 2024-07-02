package com.team4chamchi.tunastudycicd.reservation.repository;

import com.team4chamchi.tunastudycicd.reservation.aggregate.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

     Optional<Reservation> findByMember_MemberPhoneAndRoom_RoomId(String memberPhone, int roomId);
     Optional<Reservation> findByMember_MemberPhoneAndOccupiedTrue(String memberPhone);
     List<Reservation> findByOccupiedTrue();
     List<Reservation> findByOccupiedTrueAndEndDate(LocalDateTime tenMinutesAgo);
}
