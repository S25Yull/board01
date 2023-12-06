package com.seoyul.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.seoyul.board.dto.BoardDTO;
import com.seoyul.board.entity.BoardEntity;

public interface BoardSearchRepository extends JpaRepository<BoardEntity, Long> {
	Page<BoardEntity> findByboardTitleContaining(String searchKeyword);
	//List<BoardEntity> findByTitleContainingList(String searchKeyword);

}
