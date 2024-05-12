package com.example.account.util.repository;

import com.example.account.util.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // userId를 이용해서 Member 찾는다.
    Optional<Member> findByUserId(String userId);
}
