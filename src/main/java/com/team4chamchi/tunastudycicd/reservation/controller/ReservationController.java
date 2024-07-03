package com.team4chamchi.tunastudycicd.reservation.controller;

import com.team4chamchi.tunastudycicd.member.aggregate.Member;
import com.team4chamchi.tunastudycicd.member.dto.MemberDTO;
import com.team4chamchi.tunastudycicd.reservation.aggregate.Reservation;
import com.team4chamchi.tunastudycicd.reservation.dto.ReservationDTO;
import com.team4chamchi.tunastudycicd.reservation.service.ReservationService;
import com.team4chamchi.tunastudycicd.studyroom.dto.StudyroomDTO;
import com.team4chamchi.tunastudycicd.studyroom.service.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {

    private final ReservationService reservationService;
    private final StudyRoomService studyRoomService;

    @Autowired
    public ReservationController(ReservationService reservationService,
                                 StudyRoomService studyRoomService) {
        this.reservationService = reservationService;
        this.studyRoomService = studyRoomService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudyroomDTO>> findAllSeat() {
        List<StudyroomDTO> allSeatList = studyRoomService.findALlSeat();

        return ResponseEntity.ok(allSeatList);
    }

    @GetMapping("/seat")
    public ResponseEntity<List<ReservationDTO>> findAllOccupiedSeat() {
        List<ReservationDTO> reservationList = reservationService.findAllOccupiedSeat();

        return ResponseEntity.ok(reservationList);
    }

    @GetMapping("/member-check/{phone}")
    public ResponseEntity<MemberDTO> findMemberByPhone(@PathVariable("phone") String phone) {
        Optional<Member> member = reservationService.findMemberByPhone(phone);

        return member.map(value -> ResponseEntity.ok(new MemberDTO(value))).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/member")
    public ResponseEntity<MemberDTO> addMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO member = new MemberDTO(reservationService.addMember(memberDTO));

        return ResponseEntity.ok(member);
    }

    @GetMapping("/member/{phone}")
    public ResponseEntity<ReservationDTO> findReservationByPhone(@PathVariable("phone") String phone) {
        Optional<Reservation> reservation = reservationService.findReservationByPhone(phone);

        return reservation.map(value -> ResponseEntity.ok(new ReservationDTO(value))).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/reservation/{phone}/{roomId}")
    public ResponseEntity<ReservationDTO> findReservation(@PathVariable("phone") String phone,
                                                          @PathVariable("roomId") int roomId) {
        ReservationDTO reservation = reservationService.findReservationByPhoneAndSeat(phone, roomId);

        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/check-in/{reservationId}")
    public ResponseEntity<ReservationDTO> checkInReservation(@PathVariable("reservationId") int reservationId) {
        ReservationDTO reservation = reservationService.createReservation(reservationId);

        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/check-out/{reservationId}")
    public ResponseEntity<ReservationDTO> checkOutReservation(@PathVariable("reservationId") int reservationId) {
        ReservationDTO reservation = reservationService.releaseReservation(reservationId);

        return ResponseEntity.ok(reservation);
    }
}
