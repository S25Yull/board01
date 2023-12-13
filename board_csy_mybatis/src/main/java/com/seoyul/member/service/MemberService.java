package com.seoyul.member.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoyul.member.dto.MemberDTO;
import com.seoyul.member.mapper.MemberMapper;


@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
    
	//로그인정보 조회
	public MemberDTO selectMember(MemberDTO memberDTO) {
		return memberMapper.selectMember(memberDTO);
	}

	//회원가입
	public int insertMember(MemberDTO memberDTO) {
		return memberMapper.insertMember(memberDTO);
	}
	
	//이메일 중복조회
	public int selectEmailCheck(String memberEmail) {
		return memberMapper.selectEmailCheck(memberEmail);
	}

}