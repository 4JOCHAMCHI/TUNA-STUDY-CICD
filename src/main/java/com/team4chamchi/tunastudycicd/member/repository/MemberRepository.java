package com.team4chamchi.tunastudycicd.member.repository;

import com.team4chamchi.tunastudycicd.member.aggregate.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByMemberPhone(String memberPhone);
}
