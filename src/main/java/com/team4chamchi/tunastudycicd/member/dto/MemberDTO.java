package com.team4chamchi.tunastudycicd.member.dto;

import com.team4chamchi.tunastudycicd.member.aggregate.Member;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private int memberId;
    private String memberPhone;
    private String memberName;
    private String email;

    public MemberDTO(Member member) {
        this.memberId = member.getMemberId();
        this.memberPhone =  member.getMemberPhone();
        this.memberName = member.getMemberName();
        this.email = member.getEmail();
    }
}
