package com.seoyul.member.entity;

import lombok.*;
import javax.persistence.*;

import com.seoyul.member.dto.MemberDTO;

@Entity
@Setter
@Getter
@Table(name = "member_table") //테이블 이름을 정의하는 부분
public class MemberEntity {
	
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 증가하는 값이 부여됨 , PK지정
    private Long id;
    
    //일반 컬럼 지정
    @Column(unique = true) // unique 제약조건 추가
    private String memberEmail;
   
    @Column
    private String loginId;   

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    //회원가입
    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
    	MemberEntity memberEntity = new MemberEntity();
    	memberEntity.setLoginId(memberDTO.getLoginId()); //로그인 아이디
    	memberEntity.setMemberEmail(memberDTO.getMemberEmail());//dto에 담긴것을 entity객체로 넘기는것
    	memberEntity.setMemberPassword(memberDTO.getMemberPassword());
    	memberEntity.setMemberName(memberDTO.getMemberName());
    	return memberEntity;
    }
    //업데이트 (PK 는 업데이트 하지 않음)
    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
    	MemberEntity memberEntity = new MemberEntity();
    	memberEntity.setId(memberDTO.getId());
    	memberEntity.setMemberEmail(memberDTO.getMemberEmail());//dto에 담긴것을 entity객체로 넘기는것
    	memberEntity.setMemberPassword(memberDTO.getMemberPassword());
    	memberEntity.setMemberName(memberDTO.getMemberName());
    	return memberEntity;
    }
    
}