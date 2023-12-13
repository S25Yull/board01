package com.seoyul.board.entity;
//DB의 테이블 역할을 하는 클래스
import lombok.*;

import java.util.ArrayList;

import javax.persistence.*;

import com.seoyul.board.dto.BoardDTO;


import java.util.List;



@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
	
	  @Id // pk 컬럼 지정. 필수
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	    private Long id;

	    @Column(length = 20, nullable = false) // 크기 20, not null
	    private String boardWriter;

	    @Column // 크기 255, null 가능
	    private String boardPass;

	    @Column
	    private String boardTitle;

	    @Column(length = 500)
	    private String boardContents;

	    @Column
	    private int boardHits;
	    
	    @Column
	    private int fileAttached; //boardDTO에서 선언한것처럼 파일의 존재유무를 0과 1로 구분
	    
	    //부모 테이블의 정의 부모가 사라지면 자식도 함께 사라짐
	    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();
	    
	    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	    private List<CommentEntity> commentEntityList = new ArrayList<>();
	    
	    //글쓰기
	    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
	    	
	    	BoardEntity boardEntity = new BoardEntity();
	        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
	        boardEntity.setBoardPass(boardDTO.getBoardPass());
	        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
	        boardEntity.setBoardContents(boardDTO.getBoardContents());
	        boardEntity.setBoardHits(0);
	        boardEntity.setFileAttached(0); //첨부파일로 새롭게 추가한 부분, (0)은 파일이 없다는 의미
	        return boardEntity;
	    }
	    //업데이트
		public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
			BoardEntity boardEntity = new BoardEntity();
			boardEntity.setId(boardDTO.getId());
	        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
	        boardEntity.setBoardPass(boardDTO.getBoardPass());
	        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
	        boardEntity.setBoardContents(boardDTO.getBoardContents());
	        boardEntity.setBoardHits(boardDTO.getBoardHits());
	        
	        return boardEntity;
		}
		public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
			BoardEntity boardEntity = new BoardEntity();
	        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
	        boardEntity.setBoardPass(boardDTO.getBoardPass());
	        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
	        boardEntity.setBoardContents(boardDTO.getBoardContents());
	        boardEntity.setBoardHits(0);
	        boardEntity.setFileAttached(1); //첨부파일로 새롭게 추가한 부분, (1)은 파일이 있다는 의미
	        return boardEntity;
		}
		
		
}

    
    
