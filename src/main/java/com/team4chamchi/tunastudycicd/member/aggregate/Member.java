package com.team4chamchi.tunastudycicd.member.aggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team4chamchi.tunastudycicd.member.dto.MemberDTO;
import com.team4chamchi.tunastudycicd.reservation.aggregate.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberId;

    @Column(name = "member_phone")
    private String memberPhone;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Reservation> reservations;

    public Member(MemberDTO memberDTO) {
        this.memberPhone = memberDTO.getMemberPhone();
        this.memberName = memberDTO.getMemberName();
        this.email = memberDTO.getEmail();
    }
}