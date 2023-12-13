package com.seoyul.member.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seoyul.member.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 이메일로 회원 정보 조회 (select * from member_table where member_email=?)
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}