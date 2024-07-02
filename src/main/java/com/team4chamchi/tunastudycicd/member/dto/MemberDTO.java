package com.team4chamchi.tunastudycicd.member.dto;

import com.team4chamchi.tunastudycicd.member.aggregate.Member;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
@ToString
//@Data
public class MemberDTO {

    private int memberId;
    private String memberPhone;
    private String memberName;
    private String email;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MemberDTO(Member member) {
        this.memberId = member.getMemberId();
        this.memberPhone =  member.getMemberPhone();
        this.memberName = member.getMemberName();
        this.email = member.getEmail();
    }
}
