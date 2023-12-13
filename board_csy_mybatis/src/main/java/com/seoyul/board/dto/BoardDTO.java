package com.seoyul.board.dto;


import lombok.*;

//게시판 데이터를 IN↔OUT하는 통합 DTO

//@Data 어노테이션은 lombok의 모든 기능을 쓰겠다는 의미
@Data
public class BoardDTO {
	
    private Long id;
    private String title;
    private String content;
    private String attach;
    private String hit;
    
    // 변수는 카멜케이스 규칙으로 정한다 reg_info >> regInfo
    private String regInfo;
    private String regDate;
    
    
    //검색조건
    private String schKeyword;	//검색어
    private String schType;	//검색타입(제목or내용)
    
}