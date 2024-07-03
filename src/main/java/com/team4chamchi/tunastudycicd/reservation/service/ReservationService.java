package com.team4chamchi.tunastudycicd.reservation.service;

import com.team4chamchi.tunastudycicd.member.aggregate.Member;
import com.team4chamchi.tunastudycicd.member.dto.MemberDTO;
import com.team4chamchi.tunastudycicd.member.repository.MemberRepository;
import com.team4chamchi.tunastudycicd.notification.service.NotificationService;
import com.team4chamchi.tunastudycicd.reservation.aggregate.Reservation;
import com.team4chamchi.tunastudycicd.reservation.dto.ReservationDTO;
import com.team4chamchi.tunastudycicd.reservation.repository.ReservationRepository;
import com.team4chamchi.tunastudycicd.studyroom.aggregate.Studyroom;
import com.team4chamchi.tunastudycicd.studyroom.respository.StudyRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final StudyRoomRepository studyRoomRepository;
    private final NotificationService notificationService;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(MemberRepository memberRepository,
                              ReservationRepository reservationRepository,
                              StudyRoomRepository studyRoomRepository,
                              NotificationService notificationService) {
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
        this.studyRoomRepository = studyRoomRepository;
        this.notificationService = notificationService;
    }

    public List<ReservationDTO> findAllOccupiedSeat() {
        return reservationRepository.findByOccupiedTrue().stream().map(ReservationDTO::new).collect(Collectors.toList());
    }

    public Optional<Reservation> findReservationByPhone(String memberPhone) {
        return reservationRepository.findByMember_MemberPhoneAndOccupiedTrue(memberPhone);
    }

    public Optional<Member> findMemberByPhone(String memberPhone) {
        return memberRepository.findByMemberPhone(memberPhone);
    }

    public Member addMember(MemberDTO memberDTO) {
        Member member = new Member(memberDTO);

        return memberRepository.save(member);
    }

    @Transactional
    public ReservationDTO findReservationByPhoneAndSeat(String memberPhone, int roomId) {
        Member member = findMemberByPhone(memberPhone).orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        Studyroom room = studyRoomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("유효하지 않은 좌석입니다."));

        Optional<Reservation> reservation = reservationRepository.findByMember_MemberPhoneAndRoom_RoomId(memberPhone, roomId);

        if (reservation.isEmpty()) {
            Reservation newReservation = new Reservation(member, room);

            return new ReservationDTO(reservationRepository.save(newReservation));
        }

        return new ReservationDTO(reservation.get());
    }

    @Transactional
    public ReservationDTO createReservation(int reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);

        if (reservation.isPresent()) {
            Reservation foundReservation = reservation.get();

            foundReservation.setOccupied(true);
            foundReservation.setStartDate(LocalDateTime.now().withSecond(0).withNano(0));
            foundReservation.setEndDate(foundReservation.getStartDate().
                    plusHours(2).withSecond(0).withNano(0));

            String phone = "+82" + foundReservation.getMember().getMemberPhone();
            String memberName = foundReservation.getMember().getMemberName();
            String roomName = foundReservation.getRoom().getRoomName();

            notificationService.sendMessage(phone, memberName + "님, " + roomName + "번 좌석 예약되었습니다!");

            return new ReservationDTO(reservationRepository.save(foundReservation));
        } else {
            throw new IllegalArgumentException("Reservation with id " + reservationId+ " not found.");
        }
    }

    @Transactional
    public ReservationDTO releaseReservation(int reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);

        if (reservation.isPresent()) {
            Reservation foundReservation = reservation.get();

            foundReservation.setOccupied(false);
            foundReservation.setEndDate(LocalDateTime.now().withSecond(0).withNano(0));

            String phone = "+82" + foundReservation.getMember().getMemberPhone();
            String memberName = foundReservation.getMember().getMemberName();
            String roomName = foundReservation.getRoom().getRoomName();

            notificationService.sendMessage(phone, memberName + "님, " + roomName + "번 좌석 퇴실되었습니다!");

            return new ReservationDTO(reservationRepository.save(foundReservation));
        } else {
            throw new IllegalArgumentException("Reservation with id " + reservationId + " not found.");
        }
    }

    @Scheduled(cron = "0 * * * * *")
    public void tenMinutesNotification() {
        LocalDateTime tenMinutesAgo = LocalDateTime.now().plusMinutes(10).withSecond(0).withNano(0);
        List<Reservation> reservationList = reservationRepository.findByOccupiedTrueAndEndDate(tenMinutesAgo);

        for (Reservation reservation : reservationList) {
            notificationService.sendMessage("+82" + reservation.getMember().getMemberPhone(),
                    reservation.getMember().getMemberName() + "님, " +
                            reservation.getRoom().getRoomName()+ "번 좌석 퇴실 10분 전입니다.");
        }
    }

    @Scheduled(cron = "0 * * * * *")
    public void autoReleaseReservation() {
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
        List<Reservation> reservationList = reservationRepository.findByOccupiedTrueAndEndDate(now);

        for (Reservation reservation : reservationList) {
            releaseReservation(reservation.getReservationId());
        }
    }
}