package com.seoyul.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.seoyul.member.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	
	//로그인 조회
    MemberDTO selectMember(MemberDTO memberDTO);
    
    //회원 저장
    int insertMember(MemberDTO memberDTO);
    
    //이메일 중복조회
    int selectEmailCheck(String memberEmail);

}
