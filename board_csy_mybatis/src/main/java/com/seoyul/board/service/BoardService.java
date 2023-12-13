package com.seoyul.board.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoyul.board.dto.BoardDTO;
import com.seoyul.board.mapper.BoardMapper;


@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
    
	//게시판 목록 조회
	public List<BoardDTO> selectBoardList(BoardDTO boardDTO) {
		return boardMapper.selectBoardList(boardDTO);
	}

	//게시판 저장
	public int insertBoard(BoardDTO boardDTO) {
		return boardMapper.insertBoard(boardDTO);
	}
}