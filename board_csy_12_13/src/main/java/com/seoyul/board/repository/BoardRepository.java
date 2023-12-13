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
	
	//검색조건 있을때
	@Query(value = "SELECT b FROM BoardEntity b WHERE b.boardTitle LIKE %:searchKeyword% AND b.boardWriter LIKE %:searchWriter%")
	Page<BoardEntity> customSearch(@Param("searchKeyword") String searchKeyword, @Param("searchWriter") String searchWriter, Pageable pageable); 
	//버전 문제로 인한 이슈 한번이상 컨테이닝을 사용하면 에러
	//JPQL은 한 레포지토리 안에 정의해도 됨
	
	//검색조건 없을때
	@Query(value = "SELECT b FROM BoardEntity b")
	Page<BoardEntity> defaultSearch(Pageable pageable);
	
}