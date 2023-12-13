package com.seoyul.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seoyul.board.entity.BoardEntity;
import com.seoyul.board.entity.CommentEntity;

public interface  CommentRepository extends JpaRepository<CommentEntity, Long>  {
	 // select * from comment_table where board_id=? order by id desc;
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity); //Jpa 의 sql 문법 대소문자 구별이 중요함
}
