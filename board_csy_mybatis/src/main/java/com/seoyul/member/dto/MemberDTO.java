package com.seoyul.member.dto;

import lombok.*;


//회원 데이터를 IN↔OUT하는 통합 DTO
@Data
public class MemberDTO {
   
    private Long id;
    private String loginId;
    private String memberPassword;
    private String memberEmail;
    private String memberName;
    
}