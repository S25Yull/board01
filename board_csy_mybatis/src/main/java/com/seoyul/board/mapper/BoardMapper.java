package com.seoyul.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.seoyul.board.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	//게시판목록
    List<BoardDTO> selectBoardList(BoardDTO boardDTO);

    //게시판상세조회
    BoardDTO selectBoardDetail(Integer id);
    
    //게시판저장
    int insertBoard(BoardDTO board);


}