package com.seoyul.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seoyul.board.entity.BoardEntity;
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	// update board_table set board_hits=board_hits+1 where id=?
	@Modifying
	@Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
	void updateHits(@Param("id") Long id);
	Page<BoardEntity> findByBoardTitleContainingOrBoardWriterContaining(String searchKeyword,String searchWriter,Pageable pageable);
}